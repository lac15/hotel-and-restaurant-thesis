package hu.unideb.inf.thesis.hotel.core.repository;

import hu.unideb.inf.thesis.hotel.core.entitiy.ReservedTimeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservedTimeRepository extends JpaRepository<ReservedTimeEntity, Long> {

    ReservedTimeEntity findByReservedTime(Date reservedTime);

    @Query("SELECT rt FROM TableEntity t JOIN t.reservedTimes rt WHERE t.id = ?1")
    List<ReservedTimeEntity> findReservedTimesByTableId(Long roomId);
}
