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

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private TableEntity table;

    public TableReserveEntity(){}

    public TableReserveEntity(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
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

    public TableEntity getTable() {
        return table;
    }

    public void setTable(TableEntity table) {
        this.table = table;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableReserveEntity)) return false;
        if (!super.equals(o)) return false;
        TableReserveEntity that = (TableReserveEntity) o;
        return Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(table, that.table);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startTime, endTime, table);
    }

    @Override
    public String toString() {
        return "TableReserveEntity{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", table=" + table +
                '}';
    }
}