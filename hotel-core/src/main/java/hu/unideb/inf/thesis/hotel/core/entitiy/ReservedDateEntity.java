package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ReservedDates")
public class ReservedDateEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private Date reservedDate;

    public ReservedDateEntity() {
    }

    public ReservedDateEntity(Date reservedDate) {
        this.reservedDate = reservedDate;
    }

    public Date getReservedDate() {
        return reservedDate;
    }

    public void setReservedDate(Date reservedDate) {
        this.reservedDate = reservedDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ReservedDateEntity that = (ReservedDateEntity) o;

        return reservedDate.equals(that.reservedDate);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + reservedDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ReservedDateEntity{" +
                "reservedDate=" + reservedDate +
                '}';
    }
}