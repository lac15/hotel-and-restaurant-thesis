package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class RoomVo implements Serializable {

    private static final long serialVersionUID = 1648000328505763772L;

    private Long id;
    private int number;
    private List<Date> reservedDates;

    public RoomVo(){}

    public RoomVo(Long id, int number) {
        this.id = id;
        this.number = number;
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

    public List<Date> getReservedDates() {
        return reservedDates;
    }

    public void setReservedDates(List<Date> reservedDates) {
        this.reservedDates = reservedDates;
    }
}
