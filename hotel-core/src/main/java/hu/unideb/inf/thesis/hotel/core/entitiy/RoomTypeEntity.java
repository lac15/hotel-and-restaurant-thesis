package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "RoomTypes")
public class RoomTypeEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private int capacity;

    @Basic
    @Column(nullable = false)
    private int price;

    @Basic
    @Column(nullable = false)
    private String image;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<RoomEntity> rooms;

    public RoomTypeEntity() {
    }

    public RoomTypeEntity(int capacity, int price, String image) {
        this.capacity = capacity;
        this.price = price;
        this.image = image;
        this.rooms = new ArrayList<RoomEntity>();
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RoomTypeEntity that = (RoomTypeEntity) o;

        if (capacity != that.capacity) return false;
        if (price != that.price) return false;
        return rooms != null ? rooms.equals(that.rooms) : that.rooms == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + capacity;
        result = 31 * result + price;
        result = 31 * result + (rooms != null ? rooms.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "RoomTypeEntity{" +
                "capacity=" + capacity +
                ", price=" + price +
                '}';
    }
}