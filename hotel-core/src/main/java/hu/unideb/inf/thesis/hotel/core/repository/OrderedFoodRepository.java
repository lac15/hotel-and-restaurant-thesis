package hu.unideb.inf.thesis.hotel.core.repository;

import hu.unideb.inf.thesis.hotel.core.entitiy.OrderedFoodEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedFoodRepository extends JpaRepository<OrderedFoodEntity, Long> {

    OrderedFoodEntity findByQuantity(int quantity);

}
