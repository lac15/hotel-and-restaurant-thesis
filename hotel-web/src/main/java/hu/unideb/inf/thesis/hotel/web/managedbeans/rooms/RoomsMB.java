package hu.unideb.inf.thesis.hotel.web.managedbeans.rooms;

import hu.unideb.inf.thesis.hotel.client.api.service.RoomTypeService;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomTypeVo;

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

    private List<RoomTypeVo> roomTypes = new ArrayList<RoomTypeVo>();
    private RoomTypeVo roomType;

    private String selectedImage;

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

    public String getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(String selectedImage) {
        this.selectedImage = selectedImage;
    }
}
