package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;

@Entity
@Table(name = "Foods")
public class FoodEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String name;

    @Basic
    @Column(nullable = false)
    private int price;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private FoodTypeEntity type;

    public FoodEntity(){}

    public FoodEntity(String name, int price, FoodTypeEntity type) {
        this.name = name;
        this.price = price;
        this.type = type;
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

    public FoodTypeEntity getType() {
        return type;
    }

    public void setType(FoodTypeEntity type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FoodEntity that = (FoodEntity) o;

        if (price != that.price) return false;
        if (!name.equals(that.name)) return false;
        return type.equals(that.type);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + price;
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FoodEntity{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                '}';
    }
}
