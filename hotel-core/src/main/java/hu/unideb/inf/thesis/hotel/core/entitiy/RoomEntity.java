package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Rooms")
public class RoomEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private int number;

    @Basic
    @Column(nullable = false)
    private int capacity;

    @Basic
    @Column(nullable = false)
    private boolean reserved;

    public RoomEntity(){}

    public RoomEntity(int number, int capacity) {
        this.number = number;
        this.capacity = capacity;
        this.reserved = false;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RoomEntity that = (RoomEntity) o;

        if (number != that.number) return false;
        if (capacity != that.capacity) return false;
        return reserved == that.reserved;

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + number;
        result = 31 * result + capacity;
        result = 31 * result + (reserved ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoomEntity{" +
                "number=" + number +
                ", capacity=" + capacity +
                ", reserved=" + reserved +
                '}';
    }
}