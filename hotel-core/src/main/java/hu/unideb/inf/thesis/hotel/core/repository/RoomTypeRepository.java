package hu.unideb.inf.thesis.hotel.core.repository;

import hu.unideb.inf.thesis.hotel.core.entitiy.RoomTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomTypeRepository extends JpaRepository<RoomTypeEntity, Long> {

    List<RoomTypeEntity> findByCapacity(int capacity);

}
