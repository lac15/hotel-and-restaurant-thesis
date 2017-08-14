package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TableTypes")
public class TableTypeEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private int seats;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<TableEntity> tables;

    public TableTypeEntity() {
    }

    public TableTypeEntity(int seats) {
        this.seats = seats;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public List<TableEntity> getTables() {
        return tables;
    }

    public void setTables(List<TableEntity> tables) {
        this.tables = tables;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TableTypeEntity that = (TableTypeEntity) o;

        return seats == that.seats;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + seats;
        return result;
    }

    @Override
    public String toString() {
        return "TableTypeEntity{" +
                "seats=" + seats +
                '}';
    }
}