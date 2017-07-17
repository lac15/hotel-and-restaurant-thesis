package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;

public class RoomVo implements Serializable {

    private static final long serialVersionUID = 1648000328505763772L;

    private Long id;
    private int number;
    private boolean reserved;

    public RoomVo(){}

    public RoomVo(Long id, int number, int capacity, double price) {
        this.id = id;
        this.number = number;
        this.reserved = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
