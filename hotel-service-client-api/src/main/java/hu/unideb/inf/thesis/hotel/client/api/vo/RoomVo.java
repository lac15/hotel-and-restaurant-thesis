package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;

public class RoomVo implements Serializable {

    private static final long serialVersionUID = 1648000328505763772L;

    private Long id;
    private int number;
    private int capacity;
    private boolean reserved;

    public RoomVo(){}

    public RoomVo(Long id, int number, int capacity) {
        this.id = id;
        this.number = number;
        this.capacity = capacity;
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

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }
}
