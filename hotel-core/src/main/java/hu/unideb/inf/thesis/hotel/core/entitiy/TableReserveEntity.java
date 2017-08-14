package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "TableReserves")
public class TableReserveEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private Date startTime;

    @Basic
    @Column(nullable = false)
    private Date endTime;

    @OneToOne
    @JoinColumn(name = "Tables_id")
    private TableEntity tableEntity;

    public TableReserveEntity(){}

    public TableReserveEntity(Date startTime, Date endTime, TableEntity tableEntity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.tableEntity = tableEntity;
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

    public TableEntity getTableEntity() {
        return tableEntity;
    }

    public void setTableEntity(TableEntity tableEntity) {
        this.tableEntity = tableEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TableReserveEntity that = (TableReserveEntity) o;

        if (!startTime.equals(that.startTime)) return false;
        return endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + startTime.hashCode();
        result = 31 * result + endTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TableReserveEntity{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                '}';
    }
}