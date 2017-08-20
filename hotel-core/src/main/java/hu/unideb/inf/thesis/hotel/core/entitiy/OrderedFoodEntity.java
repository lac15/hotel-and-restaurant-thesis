package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "OrderedFoods")
public class OrderedFoodEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private int quantity;

    public OrderedFoodEntity(){}

    public OrderedFoodEntity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderedFoodEntity that = (OrderedFoodEntity) o;

        return quantity == that.quantity;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + quantity;
        return result;
    }

    @Override
    public String toString() {
        return "OrderedFoodEntity{" +
                "quantity=" + quantity +
                '}';
    }
}
