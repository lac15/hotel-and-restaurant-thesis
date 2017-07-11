package hu.unideb.inf.thesis.hotel.web.managedbeans.rooms;

import hu.unideb.inf.thesis.hotel.client.api.service.RoomService;
import hu.unideb.inf.thesis.hotel.client.api.vo.FoodVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "roomssMB")
public class RoomsMB {

    @EJB
    private RoomService roomService;

    private List<RoomVo> rooms = new ArrayList<RoomVo>();
    private RoomVo room;

    @PostConstruct
    public void init() {
        rooms.addAll(roomService.getRooms());
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
}
