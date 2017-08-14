package hu.unideb.inf.thesis.hotel.core.repository;

import hu.unideb.inf.thesis.hotel.core.entitiy.TableEntity;
import hu.unideb.inf.thesis.hotel.core.entitiy.TableReserveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TableReserveRepository extends JpaRepository<TableReserveEntity, Long> {

    List<TableReserveEntity> findByStartTime(Date startTime);

    List<TableReserveEntity> findByEndTime(Date endTime);

    TableReserveEntity findByStartTimeAndEndTimeAndTableEntity(Date startTime, Date endTime, TableEntity tableEntity);

}
