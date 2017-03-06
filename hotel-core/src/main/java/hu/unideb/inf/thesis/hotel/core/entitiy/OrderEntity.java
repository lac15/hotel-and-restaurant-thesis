package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Orders")
public class OrderEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private Date time;

    @Basic
    @Column(nullable = false)
    private int total;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<FoodEntity> foods;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<DrinkEntity> drinks;

    public OrderEntity(){}

    public OrderEntity(Date startTime, int total) {
        this.time = time;
        this.total = total;
        this.foods = new ArrayList<>();
        this.drinks = new ArrayList<>();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<FoodEntity> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodEntity> foods) {
        this.foods = foods;
    }

    public List<DrinkEntity> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkEntity> drinks) {
        this.drinks = drinks;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderEntity)) return false;
        if (!super.equals(o)) return false;
        OrderEntity that = (OrderEntity) o;
        return Objects.equals(time, that.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), time);
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "time=" + time +
                '}';
    }
}