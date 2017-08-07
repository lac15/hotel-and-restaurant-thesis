package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;

public class RoomVo implements Serializable {

    private static final long serialVersionUID = 1648000328505763772L;

    private Long id;
    private int number;

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
}
