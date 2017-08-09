package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RoomReserves")
public class RoomReserveEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private Date startTime;

    @Basic
    @Column(nullable = false)
    private Date endTime;

    @Basic
    @Column(nullable = false)
    private int totalPrice;

    @OneToOne
    @JoinColumn(name = "Rooms_id")
    private RoomEntity roomEntity;

    public RoomReserveEntity(){}

    public RoomReserveEntity(Date startTime, Date endTime, int totalPrice, RoomEntity roomEntity) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.totalPrice = totalPrice;
        this.roomEntity = roomEntity;
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

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public RoomEntity getRoomEntity() {
        return roomEntity;
    }

    public void setRoomEntity(RoomEntity roomEntity) {
        this.roomEntity = roomEntity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RoomReserveEntity that = (RoomReserveEntity) o;

        if (totalPrice != that.totalPrice) return false;
        if (!startTime.equals(that.startTime)) return false;
        return endTime.equals(that.endTime);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + startTime.hashCode();
        result = 31 * result + endTime.hashCode();
        result = 31 * result + totalPrice;
        return result;
    }

    @Override
    public String toString() {
        return "RoomReserveEntity{" +
                "startTime=" + startTime +
                ", endTime=" + endTime +
                ", totalPrice=" + totalPrice +
                '}';
    }
}