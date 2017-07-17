package hu.unideb.inf.thesis.hotel.web.managedbeans.rooms;

import hu.unideb.inf.thesis.hotel.client.api.service.RoomService;
import hu.unideb.inf.thesis.hotel.client.api.service.RoomTypeService;
import hu.unideb.inf.thesis.hotel.client.api.vo.FoodVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomTypeVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "roomsMB")
public class RoomsMB {

    @EJB
    private RoomTypeService roomTypeService;

    private List<RoomTypeVo> roomTypes = new ArrayList<RoomTypeVo>();
    private RoomTypeVo roomType;

    @PostConstruct
    public void init() {
        roomTypes.addAll(roomTypeService.getRoomTypes());
    }

    public List<RoomTypeVo> getRoomTypes() {
        return roomTypes;
    }

    public void setRoomTypes(List<RoomTypeVo> roomTypes) {
        this.roomTypes = roomTypes;
    }

    public RoomTypeVo getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomTypeVo roomType) {
        this.roomType = roomType;
    }
}
