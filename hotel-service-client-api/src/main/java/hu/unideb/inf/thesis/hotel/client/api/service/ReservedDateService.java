package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.ReservedDateVo;

import java.util.Date;
import java.util.List;

public interface ReservedDateService {

    List<ReservedDateVo> getReservedDates();

    ReservedDateVo saveReservedDate(ReservedDateVo reservedDateVo);

    ReservedDateVo getReservedDateById(Long id);

    ReservedDateVo getReservedDateByReservedDate(Date reservedDate);

    List<ReservedDateVo> getReservedDatesByRoomId(Long roomId);
}
