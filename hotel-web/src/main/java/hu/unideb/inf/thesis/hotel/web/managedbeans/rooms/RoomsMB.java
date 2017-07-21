package hu.unideb.inf.thesis.hotel.web.managedbeans.rooms;

import hu.unideb.inf.thesis.hotel.client.api.service.RoomReserveService;
import hu.unideb.inf.thesis.hotel.client.api.service.RoomService;
import hu.unideb.inf.thesis.hotel.client.api.service.RoomTypeService;
import hu.unideb.inf.thesis.hotel.client.api.vo.FoodVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomReserveVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomTypeVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "roomsBean")
public class RoomsMB {

    @EJB
    private RoomTypeService roomTypeService;
    @EJB
    private RoomReserveService roomReserveService;

    private List<RoomTypeVo> roomTypes = new ArrayList<RoomTypeVo>();
    private RoomTypeVo roomType;

    private RoomReserveVo roomReserveVo = new RoomReserveVo();

    private Date startTime = new Date();
    private Date endTime = new Date();

    private String selectedImage;

    @PostConstruct
    public void init() {
        roomTypes.addAll(roomTypeService.getRoomTypes());
    }

    public void addRoomReserve() {
        roomReserveVo.setStartTime(startTime);
        roomReserveVo.setEndTime(endTime);
        roomReserveService.saveRoomReserve(roomReserveVo);
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

    public RoomReserveVo getRoomReserveVo() {
        return roomReserveVo;
    }

    public void setRoomReserveVo(RoomReserveVo roomReserveVo) {
        this.roomReserveVo = roomReserveVo;
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

    public String getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(String selectedImage) {
        this.selectedImage = selectedImage;
    }
}
