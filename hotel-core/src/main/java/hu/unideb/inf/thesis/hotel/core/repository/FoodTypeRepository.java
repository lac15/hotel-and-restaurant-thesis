package hu.unideb.inf.thesis.hotel.core.repository;

import hu.unideb.inf.thesis.hotel.core.entitiy.FoodTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodTypeRepository extends JpaRepository<FoodTypeEntity, Long> {

    FoodTypeEntity findByName(String name);
}
