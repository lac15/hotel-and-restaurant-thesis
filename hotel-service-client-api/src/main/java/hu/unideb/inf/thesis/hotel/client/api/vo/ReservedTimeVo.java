package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;
import java.util.Date;

public class ReservedTimeVo implements Serializable {

    private static final long serialVersionUID = 1332850328505763772L;

    private Long id;
    private Date reservedTime;

    public ReservedTimeVo() {
    }

    public ReservedTimeVo(Long id, Date reservedTime) {
        this.id = id;
        this.reservedTime = reservedTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReservedTime() {
        return reservedTime;
    }

    public void setReservedTime(Date reservedTime) {
        this.reservedTime = reservedTime;
    }
}