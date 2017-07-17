package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;

public class RoomTypeVo implements Serializable {

    private static final long serialVersionUID = 4563200328505763772L;

    private Long id;
    private int capacity;
    private int price;

    public RoomTypeVo(){}

    public RoomTypeVo(Long id, int capacity, int price) {
        this.id = id;
        this.capacity = capacity;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
