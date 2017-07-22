package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.RoomReserveVo;

import java.util.Date;
import java.util.List;

public interface RoomReserveService {

    RoomReserveVo saveRoomReserve(RoomReserveVo roomReserveVo);

    RoomReserveVo getRoomReserveById(Long id);

    List<RoomReserveVo> getRoomReservesByStartTime(Date startTime);

    List<RoomReserveVo> getRoomReservesByEndTime(Date endTime);

}
