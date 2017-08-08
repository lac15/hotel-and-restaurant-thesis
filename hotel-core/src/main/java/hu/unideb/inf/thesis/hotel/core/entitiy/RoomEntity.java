package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Rooms")
public class RoomEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private int number;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<RoomReserveEntity> roomReserves;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<ReservedDateEntity> reservedDates;

    public RoomEntity(){}

    public RoomEntity(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<RoomReserveEntity> getRoomReserves() {
        return roomReserves;
    }

    public void setRoomReserves(List<RoomReserveEntity> roomReserves) {
        this.roomReserves = roomReserves;
    }

    public List<ReservedDateEntity> getReservedDates() {
        return reservedDates;
    }

    public void setReservedDates(List<ReservedDateEntity> reservedDates) {
        this.reservedDates = reservedDates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RoomEntity that = (RoomEntity) o;

        return number == that.number;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + number;
        return result;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "number=" + number +
                '}';
    }
}