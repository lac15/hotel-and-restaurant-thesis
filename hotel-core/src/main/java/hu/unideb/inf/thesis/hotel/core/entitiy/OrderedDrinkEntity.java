package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OrderedDrinks")
public class OrderedDrinkEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private int quantity;

    public OrderedDrinkEntity(){}

    public OrderedDrinkEntity(int quantity) {
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

        OrderedDrinkEntity that = (OrderedDrinkEntity) o;

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
        return "OrderedDrinkEntity{" +
                "quantity=" + quantity +
                '}';
    }
}