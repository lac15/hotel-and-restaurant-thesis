package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "Tables")
public class TableEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private int number;

    @Basic
    @Column(nullable = false)
    private int seats;

    @Basic
    @Column(nullable = false)
    private String description;

    @Basic
    @Column(nullable = false)
    private boolean reserved;

    @Basic
    @Column(nullable = true)
    private Date reserveTime;

    public TableEntity(){}

    public TableEntity(int number, int seats, String description, Date reserveTime) {
        this.number = number;
        this.seats = seats;
        this.description = description;
        this.reserved = false;
        this.reserveTime = reserveTime;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TableEntity)) return false;
        if (!super.equals(o)) return false;
        TableEntity that = (TableEntity) o;
        return number == that.number &&
                seats == that.seats &&
                reserved == that.reserved &&
                Objects.equals(description, that.description) &&
                Objects.equals(reserveTime, that.reserveTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), number, seats, description, reserved, reserveTime);
    }

    @Override
    public String toString() {
        return "TableEntity{" +
                "number=" + number +
                ", seats=" + seats +
                ", reserved=" + reserved +
                ", reserveTime=" + reserveTime +
                '}';
    }
}