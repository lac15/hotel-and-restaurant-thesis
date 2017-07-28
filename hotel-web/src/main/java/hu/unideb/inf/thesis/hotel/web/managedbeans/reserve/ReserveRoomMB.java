package hu.unideb.inf.thesis.hotel.web.managedbeans.reserve;

import hu.unideb.inf.thesis.hotel.client.api.service.RoomReserveService;
import hu.unideb.inf.thesis.hotel.client.api.service.RoomService;
import hu.unideb.inf.thesis.hotel.client.api.service.RoomTypeService;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomReserveVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomTypeVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomVo;
import org.primefaces.model.DefaultScheduleEvent;
import org.primefaces.model.DefaultScheduleModel;
import org.primefaces.model.LazyScheduleModel;
import org.primefaces.model.ScheduleModel;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "reserveRoomBean")
public class ReserveRoomMB {

    @EJB
    private RoomReserveService roomReserveService;
    @EJB
    private RoomService roomService;
    @EJB
    private RoomTypeService roomTypeService;

    private RoomReserveVo roomReserveVo = new RoomReserveVo();

    private List<RoomTypeVo> roomTypes = new ArrayList<RoomTypeVo>();
    private Long roomTypeId;

    private List<RoomVo> rooms = new ArrayList<RoomVo>();
    private Long roomId;

    private Date startTime = new Date();
    private Date endTime = new Date();

    private ScheduleModel reservationModel = new DefaultScheduleModel();

    @PostConstruct
    public void init() {
        roomTypes.addAll(roomTypeService.getRoomTypes());
    }

    public void addRoomReserve() {
        //Save reservation to database if it can be reserved (not reserved yet)
        //if () {
            roomReserveVo.setStartTime(startTime);
            roomReserveVo.setEndTime(endTime);
            roomReserveVo.setTotalPrice(/*(endTime - startTime) *  */
                    roomTypeService.getRoomTypeById(roomTypeId).getPrice());
            roomReserveService.saveRoomReserve(roomReserveVo);
        //}
/*
        //Has to iterate from startTime to endTime
        List<Date> newDates = new ArrayList<Date>();
        newDates.add(startTime);
        newDates.add(endTime);
        //Add reserved dates to the selected room's reservedDates list
        room.getReservedDates().addAll(newDates);
        //Save the room's changes to the database
        roomService.saveRoom(room);*/
    }

    public void onRoomTypeChange() {
        if (roomTypeId != null) {
            rooms = roomTypeService.getRoomsByRoomTypeId(roomTypeId);

            for (RoomVo room : rooms) {
                for (Date reservedDate : roomService.getRoomById(room.getId()).getReservedDates()) {
                    reservationModel.addEvent(new DefaultScheduleEvent(
                            "Room " + room.getNumber() + " is reserved", reservedDate, reservedDate));
                }
            }
        }
    }

    public RoomReserveVo getRoomReserveVo() {
        return roomReserveVo;
    }

    public void setRoomReserveVo(RoomReserveVo roomReserveVo) {
        this.roomReserveVo = roomReserveVo;
    }

    public RoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
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

    public ScheduleModel getReservationModel() {
        return reservationModel;
    }

    public void setReservationModel(ScheduleModel reservationModel) {
        this.reservationModel = reservationModel;
    }
}
