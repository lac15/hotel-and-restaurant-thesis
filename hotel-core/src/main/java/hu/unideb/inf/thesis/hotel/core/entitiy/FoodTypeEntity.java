package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "FoodTypes")
public class FoodTypeEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private String name;

    public FoodTypeEntity(){}

    public FoodTypeEntity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
