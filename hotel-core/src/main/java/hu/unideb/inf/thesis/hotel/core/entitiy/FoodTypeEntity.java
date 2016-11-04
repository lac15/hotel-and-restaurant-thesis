package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "FoodTypes")
public class FoodTypeEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<FoodEntity> foods;

    public FoodTypeEntity(){}

    public FoodTypeEntity(String name) {
        this.name = name;
        this.foods = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FoodEntity> getFoods() {
        return foods;
    }

    public void setFoods(List<FoodEntity> foods) {
        this.foods = foods;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        FoodTypeEntity that = (FoodTypeEntity) o;

        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FoodTypeEntity{" +
                "name='" + name + '\'' +
                '}';
    }
}
