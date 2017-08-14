package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.TableReserveVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.TableVo;

import java.util.Date;
import java.util.List;

public interface TableReserveService {

    TableReserveVo saveTableReserve(TableReserveVo tableReserveVo, TableVo tableVo);

    TableReserveVo getTableReserveById(Long id);

    List<TableReserveVo> getTableReservesByStartTime(Date startTime);

    List<TableReserveVo> getTableReservesByEndTime(Date endTime);

}
