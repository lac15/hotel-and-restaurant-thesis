package hu.unideb.inf.thesis.hotel.core.repository;

import hu.unideb.inf.thesis.hotel.core.entitiy.OrderedDrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedDrinkRepository extends JpaRepository<OrderedDrinkEntity, Long> {

    OrderedDrinkEntity findByQuantity(int quantity);

}
