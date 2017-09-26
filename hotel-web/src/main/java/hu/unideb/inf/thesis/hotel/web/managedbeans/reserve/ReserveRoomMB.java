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

@ManagedBean(name = "reserveRoomBean")
@ViewScoped
public class ReserveRoomMB implements Serializable {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(ReserveRoomMB.class);

    @EJB
    private RoomReserveService roomReserveService;
    @EJB
    private RoomService roomService;
    @EJB
    private RoomTypeService roomTypeService;
    @EJB
    private ReservedDateService reservedDateService;
    @EJB
    private UserService userService;
    @EJB
    private MailService mailService;

    private ResourceBundle bundle;
    private String locale;

    private RoomReserveVo roomReserveVo = new RoomReserveVo();

    private List<RoomTypeVo> roomTypes = new ArrayList<RoomTypeVo>();
    private Long roomTypeId;
    private int roomTypeCapacity;

    private List<RoomVo> rooms = new ArrayList<RoomVo>();
    private RoomVo roomVo;
    private Long roomId;
    private int roomPrice;

    private Date startTime;
    private Date endTime;
    private int totalPrice;

    private UserVo userVo;

    private ScheduleModel roomReserveModel = new DefaultScheduleModel();

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

        roomTypes.addAll(roomTypeService.getRoomTypes());
        roomPrice = 0;
    }

    public void addRoomReserve() {
        LocalDateTime ldtStart = LocalDateTime.ofInstant(startTime.toInstant(), ZoneId.systemDefault());
        LocalDateTime ldtEnd = LocalDateTime.ofInstant(endTime.toInstant(), ZoneId.systemDefault());

        LocalDateTime ldtEndPlus = ldtEnd.plusDays(1);

        boolean contains = false;
        for (LocalDateTime date = ldtStart; date.isBefore(ldtEndPlus); date = date.plusDays(1)) {
            Date normalDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());

            for (ReservedDateVo reservedDate : reservedDateService.getReservedDatesByRoomId(roomId)) {
                if (reservedDate.getReservedDate().compareTo(normalDate) == 0) {
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
            int days = 0;
            for (LocalDateTime date = ldtStart; date.isBefore(ldtEndPlus); date = date.plusDays(1)) {
                days++;
            }

            roomTypeCapacity = roomTypeService.getRoomTypeById(roomTypeId).getCapacity();

            roomReserveVo.setStartTime(startTime);
            roomReserveVo.setEndTime(endTime);
            totalPrice = days * roomTypeService.getRoomTypeById(roomTypeId).getPrice();
            roomReserveVo.setTotalPrice(totalPrice);

            RoomReserveVo roomReserveVoForUser = roomReserveService.saveRoomReserve(roomReserveVo, roomVo);

            userService.addRoomReserveToUser(userVo, roomReserveVoForUser);

            for (LocalDateTime date = ldtStart; date.isBefore(ldtEndPlus); date = date.plusDays(1)) {
                Date normalDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());

                ReservedDateVo reservedDateVo = new ReservedDateVo();
                reservedDateVo.setReservedDate(normalDate);

                roomService.addReservedDateToRoom(roomVo, reservedDateService.saveReservedDate(reservedDateVo));
            }

            sendReservationDetails();

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('reservationDialog').show();");
        }
    }

    public void onRoomTypeChange() {
        if (roomTypeId != null) {
            rooms = roomTypeService.getRoomsByRoomTypeId(roomTypeId);

            roomPrice = roomTypeService.getRoomTypeById(roomTypeId).getPrice();
        }
    }

    public void onRoomNumberChange() {
        if (roomId != null) {
            roomVo = roomService.getRoomById(roomId);

            LocalDateTime today = now();
            today = today.withHour(0).withMinute(0).withSecond(0).withNano(0);

            Date todayDate = Date.from(today.atZone(ZoneId.systemDefault()).toInstant());

            roomReserveModel.getEvents().clear();

            for (ReservedDateVo reservedDate : reservedDateService.getReservedDatesByRoomId(roomId)) {
                if ( reservedDate.getReservedDate().compareTo(todayDate) >= 0 ) {
                    roomReserveModel.addEvent(new DefaultScheduleEvent(
                            bundle.getString("schedule.room") + " " + roomVo.getNumber() + " "
                                    + bundle.getString("schedule.room.reserved"), reservedDate.getReservedDate(),
                            reservedDate.getReservedDate(), true));
                }
            }
        }
    }

    public void sendReservationDetails() {
        String message = bundle.getString("email.roomreserve.dear") + " " + userVo.getFirstname() + " "
                + userVo.getLastname() + "!<br>";
        message += bundle.getString("email.roomreserve.message");
        message += bundle.getString("email.roomreserve.roomtype") + " "
                + roomTypeService.getRoomTypeById(roomTypeId).getCapacity() + " "
                + bundle.getString("email.roomreserve.roomtype.ending");
        message += bundle.getString("email.roomreserve.roomnumber") + " " + roomVo.getNumber() + "<br>";
        message += bundle.getString("email.roomreserve.from") + " " + startTime + "<br>";
        message += bundle.getString("email.roomreserve.to") + " " + endTime + "<br>";
        message += bundle.getString("email.roomreserve.totalprice") + " " + totalPrice + " HUF<br>";
        message += bundle.getString("email.roomreserve.endmessage");

        try {
            mailService.sendMail("noreply@fourseasons.hu", userVo.getEmail(), bundle.getString("email.roomreserve.subject"), message);

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

    public RoomReserveVo getRoomReserveVo() {
        return roomReserveVo;
    }

    public void setRoomReserveVo(RoomReserveVo roomReserveVo) {
        this.roomReserveVo = roomReserveVo;
    }

    public List<RoomTypeVo> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(List<RoomTypeVo> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public int getRoomTypeCapacity() {
        return roomTypeCapacity;
    }

    public void setRoomTypeCapacity(int roomTypeCapacity) {
        this.roomTypeCapacity = roomTypeCapacity;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public List<RoomVo> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomVo> rooms) {
        this.rooms = rooms;
    }

    public RoomVo getRoomVo() {
        return roomVo;
    }

    public void setRoomVo(RoomVo roomVo) {
        this.roomVo = roomVo;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public ScheduleModel getRoomReserveModel() {
        return roomReserveModel;
    }

    public void setRoomReserveModel(ScheduleModel roomReserveModel) {
        this.roomReserveModel = roomReserveModel;
    }
}
