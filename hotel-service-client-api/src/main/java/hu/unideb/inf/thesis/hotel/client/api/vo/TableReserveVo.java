package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;
import java.util.Date;

public class TableReserveVo implements Serializable {

    private static final long serialVersionUID = 567400328505763772L;

    private Long id;
    private Date startTime;
    private Date endTime;

    public TableReserveVo(){}

    public TableReserveVo(Long id, Date startTime, Date endTime ) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
