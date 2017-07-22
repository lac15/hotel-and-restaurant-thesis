package hu.unideb.inf.thesis.hotel.web.managedbeans.reserve;

import hu.unideb.inf.thesis.hotel.client.api.service.RoomReserveService;
import hu.unideb.inf.thesis.hotel.client.api.service.RoomService;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomReserveVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomVo;

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

    private RoomReserveVo roomReserveVo = new RoomReserveVo();

    private List<RoomVo> rooms = new ArrayList<RoomVo>();
    private RoomVo room;

    private Date startTime = new Date();
    private Date endTime = new Date();

    @PostConstruct
    public void init() { rooms.addAll(roomService.getRooms());}

    public void addRoomReserve() {
        //Save reservation to database if it can be reserved (not reserved yet)
        //if () {
            roomReserveVo.setStartTime(startTime);
            roomReserveVo.setEndTime(endTime);
            roomReserveService.saveRoomReserve(roomReserveVo);
        //}

        //Has to iterate from startTime to endTime
        List<Date> newDates = new ArrayList<Date>();
        newDates.add(startTime);
        newDates.add(endTime);
        //Add reserved dates to the selected room's reservedDates list
        room.getReservedDates().addAll(newDates);
        //Save the room's changes to the database
        roomService.saveRoom(room);
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

    public List<RoomVo> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomVo> rooms) {
        this.rooms = rooms;
    }

    public RoomVo getRoom() {
        return room;
    }

    public void setRoom(RoomVo room) {
        this.room = room;
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
}
