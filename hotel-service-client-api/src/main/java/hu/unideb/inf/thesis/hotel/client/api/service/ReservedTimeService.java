package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.ReservedTimeVo;

import java.util.Date;
import java.util.List;

public interface ReservedTimeService {

    List<ReservedTimeVo> getReservedTimes();

    ReservedTimeVo saveReservedTime(ReservedTimeVo reservedTimeVo);

    ReservedTimeVo getReservedTimeById(Long id);

    ReservedTimeVo getReservedTimeByReservedTime(Date reservedTime);

    List<ReservedTimeVo> getReservedTimesByTableId(Long tableId);
}
