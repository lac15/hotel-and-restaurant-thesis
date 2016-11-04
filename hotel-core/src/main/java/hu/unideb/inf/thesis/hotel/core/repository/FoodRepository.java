package hu.unideb.inf.thesis.hotel.core.repository;

import hu.unideb.inf.thesis.hotel.core.entitiy.FoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodEntity, Long> {

    FoodEntity findByName(String name);

    FoodEntity findByPrice(int price);

    @Query("SELECT f FROM FoodTypeEntity t JOIN t.foods f WHERE t.name = ?1")
    List<FoodEntity> findFoodsByFoodTypeEntityName(String typeName);
}
