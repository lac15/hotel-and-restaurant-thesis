package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;
import java.util.Date;

public class OrderVo implements Serializable {

    private static final long serialVersionUID = 5346000328505763772L;

    private Long id;
    private Date time;
    private int totalPrice;

    public OrderVo(){}

    public OrderVo(Long id, Date time, int totalPrice) {
        this.id = id;
        this.time = time;
        this.totalPrice = totalPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
