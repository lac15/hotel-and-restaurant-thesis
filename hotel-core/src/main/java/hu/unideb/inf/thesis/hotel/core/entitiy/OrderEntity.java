package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Orders")
public class OrderEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private Date time;

    @Basic
    @Column(nullable = false)
    private int totalPrice;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<OrderedFoodEntity> orderedFoods;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<OrderedDrinkEntity> orderedDrinks;

    public OrderEntity() {
    }

    public OrderEntity(Date time, int totalPrice) {
        this.time = time;
        this.totalPrice = totalPrice;
        this.orderedFoods = new ArrayList<OrderedFoodEntity>();
        this.orderedDrinks = new ArrayList<OrderedDrinkEntity>();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderedFoodEntity> getOrderedFoods() {
        return orderedFoods;
    }

    public void setOrderedFoods(List<OrderedFoodEntity> orderedFoods) {
        this.orderedFoods = orderedFoods;
    }

    public List<OrderedDrinkEntity> getOrderedDrinks() {
        return orderedDrinks;
    }

    public void setOrderedDrinks(List<OrderedDrinkEntity> orderedDrinks) {
        this.orderedDrinks = orderedDrinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderEntity that = (OrderEntity) o;

        if (totalPrice != that.totalPrice) return false;
        return time.equals(that.time);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + time.hashCode();
        result = 31 * result + totalPrice;
        return result;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "time=" + time +
                ", totalPrice=" + totalPrice +
                '}';
    }
}