package hu.unideb.inf.thesis.hotel.core.entitiy;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "ReservedTimes")
public class ReservedTimeEntity extends BaseEntity {

    @Basic
    @Column(nullable = false)
    private Date reservedTime;

    public ReservedTimeEntity() {
    }

    public ReservedTimeEntity(Date reservedTime) {
        this.reservedTime = reservedTime;
    }

    public Date getReservedTime() {
        return reservedTime;
    }

    public void setReservedTime(Date reservedTime) {
        this.reservedTime = reservedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ReservedTimeEntity that = (ReservedTimeEntity) o;

        return reservedTime.equals(that.reservedTime);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + reservedTime.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ReservedTimeEntity{" +
                "reservedTime=" + reservedTime +
                '}';
    }
}