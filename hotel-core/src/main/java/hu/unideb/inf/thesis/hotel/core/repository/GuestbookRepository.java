package hu.unideb.inf.thesis.hotel.core.repository;

import hu.unideb.inf.thesis.hotel.core.entitiy.GuestbookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface GuestbookRepository extends JpaRepository<GuestbookEntity, Long> {

    GuestbookEntity findByName(String name);

    GuestbookEntity findByTime(Date time);

}
