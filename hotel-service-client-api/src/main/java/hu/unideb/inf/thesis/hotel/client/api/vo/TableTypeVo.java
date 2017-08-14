package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;

public class TableTypeVo implements Serializable {

    private static final long serialVersionUID = 2361140328505763772L;

    private Long id;
    private int seats;

    public TableTypeVo(){}

    public TableTypeVo(Long id, int seats) {
        this.id = id;
        this.seats = seats;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSeats() {

        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
