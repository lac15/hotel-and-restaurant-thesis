package hu.unideb.inf.thesis.hotel.web.managedbeans.reserve;

import hu.unideb.inf.thesis.hotel.client.api.service.*;
import hu.unideb.inf.thesis.hotel.client.api.vo.*;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.ScheduleModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "reserveRoomBean")
@ViewScoped
public class ReserveRoomMB {

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

    private RoomReserveVo roomReserveVo = new RoomReserveVo();

    private List<RoomTypeVo> roomTypes = new ArrayList<RoomTypeVo>();
    private Long roomTypeId;

    private List<RoomVo> rooms = new ArrayList<RoomVo>();
    private RoomVo roomVo;
    private Long roomId;

    private Date startTime;
    private Date endTime;

    private UserVo userVo;

    private ScheduleModel reservationModel = new DefaultScheduleModel();

    @PostConstruct
    public void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        userVo = userService.getUserByName(username);

        roomTypes.addAll(roomTypeService.getRoomTypes());
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
            System.out.println("Nem szabad a választott szoba az adott időszakban!");
        } else {
            int days = 0;
            for (LocalDateTime date = ldtStart; date.isBefore(ldtEndPlus); date = date.plusDays(1)) {
                days++;
            }

            roomReserveVo.setStartTime(startTime);
            roomReserveVo.setEndTime(endTime);
            roomReserveVo.setTotalPrice(days * roomTypeService.getRoomTypeById(roomTypeId).getPrice());

            RoomReserveVo roomReserveVoForUser = roomReserveService.saveRoomReserve(roomReserveVo, roomVo);

            userService.addRoomReserveToUser(userVo, roomReserveVoForUser);

            for (LocalDateTime date = ldtStart; date.isBefore(ldtEndPlus); date = date.plusDays(1)) {
                Date normalDate = Date.from(date.atZone(ZoneId.systemDefault()).toInstant());

                ReservedDateVo reservedDateVo = new ReservedDateVo();
                reservedDateVo.setReservedDate(normalDate);

                roomService.addReservedDateToRoom(roomVo, reservedDateService.saveReservedDate(reservedDateVo));
            }
        }
    }

    public void onRoomTypeChange() {
        if (roomTypeId != null) {
            rooms = roomTypeService.getRoomsByRoomTypeId(roomTypeId);
        }
    }

    public void onRoomNumberChange() {
        if (roomId != null) {
            roomVo = roomService.getRoomById(roomId);

            reservationModel.getEvents().clear();

            for (ReservedDateVo reservedDate : reservedDateService.getReservedDatesByRoomId(roomId)) {
                reservationModel.addEvent(new DefaultScheduleEvent(
                        "Room " + roomVo.getNumber() + " is reserved", reservedDate.getReservedDate(),
                        reservedDate.getReservedDate(), true));
            }
        }
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

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
    }

    public ScheduleModel getReservationModel() {
        return reservationModel;
    }

    public void setReservationModel(ScheduleModel reservationModel) {
        this.reservationModel = reservationModel;
    }
}
