package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "RoomReserves")
public class RoomReserveEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private Date startTime;

    @Basic
    @Column(nullable = false)
    private Date endTime;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private RoomEntity room;

    public RoomReserveEntity(){}

    public RoomReserveEntity(Date startTime, Date endTime) {
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

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoomReserveEntity)) return false;
        if (!super.equals(o)) return false;
        RoomReserveEntity that = (RoomReserveEntity) o;
        return Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(room, that.room);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), startTime, endTime, room);
    }

    @Override
    public String toString() {
        return "RoomReserveEntity{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", room=" + room +
                '}';
    }
}