package hu.unideb.inf.thesis.hotel.web.managedbeans.reserve;

import hu.unideb.inf.thesis.hotel.client.api.exception.EmailSendingException;
import hu.unideb.inf.thesis.hotel.client.api.service.*;
import hu.unideb.inf.thesis.hotel.client.api.vo.*;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static java.time.LocalDateTime.now;

@ManagedBean(name = "reserveTableBean")
@ViewScoped
public class ReserveTableMB implements Serializable{

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReserveTableMB.class);

    @EJB
    private TableReserveService tableReserveService;
    @EJB
    private TableService tableService;
    @EJB
    private TableTypeService tableTypeService;
    @EJB
    private ReservedTimeService reservedTimeService;
    @EJB
    private UserService userService;
    @EJB
    private MailService mailService;

    private ResourceBundle bundle;
    private String locale;

    private TableReserveVo tableReserveVo = new TableReserveVo();

    private List<TableTypeVo> tableTypes = new ArrayList<TableTypeVo>();
    private Long tableTypeId;

    private List<TableVo> tables = new ArrayList<TableVo>();
    private TableVo tableVo;
    private Long tableId;

    private Date startTime;
    private Date endTime;

    private UserVo userVo;

    private ScheduleModel tableReserveModel = new DefaultScheduleModel();

    @PostConstruct
    public void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        userVo = userService.getUserByUsername(username);

        try {
            bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            locale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
            locale = Locale.ENGLISH.getLanguage();
        }

        tableTypes.addAll(tableTypeService.getTableTypes());
    }

    public void addTableReserve() {
        LocalDateTime ldtStart = LocalDateTime.ofInstant(startTime.toInstant(), ZoneId.systemDefault());
        LocalDateTime ldtEnd = LocalDateTime.ofInstant(endTime.toInstant(), ZoneId.systemDefault());

        boolean contains = false;
        for (LocalDateTime time = ldtStart; time.isBefore(ldtEnd); time = time.plusHours(1)) {
            Date normalTime = Date.from(time.atZone(ZoneId.systemDefault()).toInstant());

            for (ReservedTimeVo reservedTime : reservedTimeService.getReservedTimesByTableId(tableId)) {
                if (reservedTime.getReservedTime().compareTo(normalTime) == 0) {
                    contains = true;
                    break;
                }
            }

            if (contains) {
                break;
            }
        }

        if (contains) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('alreadyReservedWarningDialog').show();");
        } else {
            tableReserveVo.setStartTime(startTime);
            tableReserveVo.setEndTime(endTime);

            TableReserveVo tableReserveVoForUser = tableReserveService.saveTableReserve(tableReserveVo, tableVo);

            userService.addTableReserveToUser(userVo, tableReserveVoForUser);

            for (LocalDateTime time = ldtStart; time.isBefore(ldtEnd); time = time.plusHours(1)) {
                Date normalTime = Date.from(time.atZone(ZoneId.systemDefault()).toInstant());

                ReservedTimeVo reservedTimeVo = new ReservedTimeVo();
                reservedTimeVo.setReservedTime(normalTime);

                tableService.addReservedTimeToTable(tableVo, reservedTimeService.saveReservedTime(reservedTimeVo));
            }

            sendReservationDetails();

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('reservationDialog').show();");
        }
    }

    public void onTableTypeChange() {
        if (tableTypeId != null) {
            tables = tableTypeService.getTablesByTableTypeId(tableTypeId);
        }
    }

    public void onTableNumberChange() {
        if (tableId != null) {
            tableVo = tableService.getTableById(tableId);

            LocalDateTime today = now();
            today = today.withHour(0).withMinute(0).withSecond(0).withNano(0);

            Date todayTime = Date.from(today.atZone(ZoneId.systemDefault()).toInstant());

            tableReserveModel.getEvents().clear();

            for (ReservedTimeVo reservedTime : reservedTimeService.getReservedTimesByTableId(tableId)) {
                if ( reservedTime.getReservedTime().compareTo(todayTime) >= 0 ) {
                    LocalDateTime reservedTimePlus = LocalDateTime.ofInstant(reservedTime.getReservedTime().toInstant(),
                            ZoneId.systemDefault());
                    reservedTimePlus = reservedTimePlus.plusHours(1);
                    Date normalTimePlus = Date.from(reservedTimePlus.atZone(ZoneId.systemDefault()).toInstant());

                    tableReserveModel.addEvent(new DefaultScheduleEvent(
                            bundle.getString("schedule.table") + " " + tableVo.getNumber() + " "
                                    + bundle.getString("schedule.table.reserved"), reservedTime.getReservedTime(),
                            normalTimePlus));
                }
            }
        }
    }

    public void onStartTimeChange() {
        LocalDateTime ldtNewEndTime = LocalDateTime.ofInstant(startTime.toInstant(), ZoneId.systemDefault());

        ldtNewEndTime = ldtNewEndTime.plusHours(1);

        endTime = Date.from(ldtNewEndTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public void sendReservationDetails() {
        String message = bundle.getString("email.tablereserve.dear") + " " + userVo.getFirstname() + " "
                + userVo.getLastname() + "!<br>";
        message += bundle.getString("email.tablereserve.message");
        message += bundle.getString("email.tablereserve.tabletype") + " "
                + tableTypeService.getTableTypeById(tableTypeId).getSeats() + " "
                + bundle.getString("email.tablereserve.tabletype.ending");
        message += bundle.getString("email.tablereserve.tablenumber") + " " + tableVo.getNumber() + "<br>";
        message += bundle.getString("email.tablereserve.from") + " " + startTime + "<br>";
        message += bundle.getString("email.tablereserve.to") + " " + endTime + "<br>";
        message += bundle.getString("email.tablereserve.endmessage");

        try {
            mailService.sendMail("noreply@fourseasons.hu", userVo.getEmail(), bundle.getString("email.tablereserve.subject"), message);

            LOGGER.info(bundle.getString("email.logger.success"));
        } catch (EmailSendingException e) {
            LOGGER.info(bundle.getString("email.logger.error"));
            e.printStackTrace();
        }
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public TableReserveVo getTableReserveVo() {
        return tableReserveVo;
    }

    public void setTableReserveVo(TableReserveVo tableReserveVo) {
        this.tableReserveVo = tableReserveVo;
    }

    public List<TableTypeVo> getTableTypes() {
        return tableTypes;
    }

    public void setTableTypes(List<TableTypeVo> tableTypes) {
        this.tableTypes = tableTypes;
    }

    public Long getTableTypeId() {
        return tableTypeId;
    }

    public void setTableTypeId(Long tableTypeId) {
        this.tableTypeId = tableTypeId;
    }

    public List<TableVo> getTables() {
        return tables;
    }

    public void setTables(List<TableVo> tables) {
        this.tables = tables;
    }

    public TableVo getTableVo() {
        return tableVo;
    }

    public void setTableVo(TableVo tableVo) {
        this.tableVo = tableVo;
    }

    public Long getTableId() {
        return tableId;
    }

    public void setTableId(Long tableId) {
        this.tableId = tableId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public ScheduleModel getTableReserveModel() {
        return tableReserveModel;
    }

    public void setTableReserveModel(ScheduleModel tableReserveModel) {
        this.tableReserveModel = tableReserveModel;
    }
}