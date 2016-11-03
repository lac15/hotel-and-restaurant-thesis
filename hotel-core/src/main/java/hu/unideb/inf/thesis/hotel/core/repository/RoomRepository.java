package hu.unideb.inf.thesis.hotel.core.repository;

import hu.unideb.inf.thesis.hotel.core.entitiy.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    RoomEntity findByNumber(int number);

}
