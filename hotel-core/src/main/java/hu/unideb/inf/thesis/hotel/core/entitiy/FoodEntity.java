package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Foods")
public class FoodEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String name;

    @Basic
    @Column(nullable = false)
    private int price;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<OrderedFoodEntity> orderedFoods;

    public FoodEntity(){}

    public FoodEntity(String name, int price) {
        this.name = name;
        this.price = price;
        this.orderedFoods = new ArrayList<OrderedFoodEntity>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<OrderedFoodEntity> getOrderedFoods() {
        return orderedFoods;
    }

    public void setOrderedFoods(List<OrderedFoodEntity> orderedFoods) {
        this.orderedFoods = orderedFoods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FoodEntity that = (FoodEntity) o;

        if (price != that.price) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "FoodEntity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
