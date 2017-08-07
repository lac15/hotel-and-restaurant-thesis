package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;
import java.util.Date;

public class ReservedDateVo implements Serializable {

    private static final long serialVersionUID = 2589230328505763772L;

    private Long id;
    private Date reservedDate;

    public ReservedDateVo(){}

    public ReservedDateVo(Long id, Date reservedDate) {
        this.id = id;
        this.reservedDate = reservedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(Date reservedDate) {
        this.reservedDate = reservedDate;
    }
}
