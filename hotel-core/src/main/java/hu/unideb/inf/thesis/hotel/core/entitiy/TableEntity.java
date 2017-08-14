package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Tables")
public class TableEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private int number;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    private List<ReservedTimeEntity> reservedTimes;

    public TableEntity() {
    }

    public TableEntity(int number) {
        this.number = number;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<ReservedTimeEntity> getReservedTimes() {
        return reservedTimes;
    }

    public void setReservedTimes(List<ReservedTimeEntity> reservedTimes) {
        this.reservedTimes = reservedTimes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        TableEntity that = (TableEntity) o;

        return number == that.number;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + number;
        return result;
    }

    @Override
    public String toString() {
        return "TableEntity{" +
                "number=" + number +
                '}';
    }
}