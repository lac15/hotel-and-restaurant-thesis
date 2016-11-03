package hu.unideb.inf.thesis.hotel.core.repository;

import hu.unideb.inf.thesis.hotel.core.entitiy.DrinkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrinkRepository extends JpaRepository<DrinkEntity, Long> {

    DrinkEntity findByName(String name);

    DrinkEntity findByPrice(int price);
}
