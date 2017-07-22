package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.RoomVo;

import java.util.Date;
import java.util.List;

public interface RoomService {

    List<RoomVo> getRooms();

    RoomVo saveRoom(RoomVo roomVo);

    RoomVo getRoomById(Long id);

    RoomVo getRoomByNumber(int number);

}
