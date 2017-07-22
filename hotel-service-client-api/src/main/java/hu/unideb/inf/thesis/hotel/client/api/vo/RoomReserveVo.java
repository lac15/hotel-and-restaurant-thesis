package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;
import java.util.Date;

public class RoomReserveVo implements Serializable {

    private static final long serialVersionUID = 675400328505763772L;

    private Long id;
    private Date startTime;
    private Date endTime;
    private int totalPrice;

    public RoomReserveVo(){}

    public RoomReserveVo(Long id, Date startTime, Date endTime, int totalPrice ) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
