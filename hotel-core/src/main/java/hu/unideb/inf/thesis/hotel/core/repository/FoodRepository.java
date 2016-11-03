package hu.unideb.inf.thesis.hotel.core.repository;

import hu.unideb.inf.thesis.hotel.core.entitiy.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Long> {

    FoodEntity findByName(String name);

    FoodEntity findByPrice(int price);
}
