package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;
import java.util.Date;

public class TableVo implements Serializable {

    private static final long serialVersionUID = 6754000328505763772L;

    private Long id;
    private int number;
    private int seats;
    private String description;
    private boolean reserved;
    private Date reserveTime;

    public TableVo(){}

    public TableVo(Long id, int number, int seats, String description, Date reserveTime) {
        this.id = id;
        this.number = number;
        this.seats = seats;
        this.description = description;
        this.reserved = false;
        this.reserveTime = reserveTime;
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

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public Date getReserveTime() {
        return reserveTime;
    }

    public void setReserveTime(Date reserveTime) {
        this.reserveTime = reserveTime;
    }
}
