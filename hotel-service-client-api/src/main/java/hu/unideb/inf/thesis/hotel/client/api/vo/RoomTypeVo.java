package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;

public class RoomTypeVo implements Serializable {

    private static final long serialVersionUID = 4563200328505763772L;

    private Long id;
    private int capacity;
    private int price;
    private String image;

    public RoomTypeVo(){}

    public RoomTypeVo(Long id, int capacity, int price, String image) {
        this.id = id;
        this.capacity = capacity;
        this.price = price;
        this.image = image;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
