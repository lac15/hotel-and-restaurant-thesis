package hu.unideb.inf.thesis.hotel.core.repository;

import hu.unideb.inf.thesis.hotel.core.entitiy.ReservedDateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReservedDateRepository extends JpaRepository<ReservedDateEntity, Long> {

    ReservedDateEntity findByReservedDate(Date reservedDate);

    @Query("SELECT rd FROM RoomEntity r JOIN r.reservedDates rd WHERE r.id = ?1")
    List<ReservedDateEntity> findReservedDatesByRoomId(Long roomId);
}
