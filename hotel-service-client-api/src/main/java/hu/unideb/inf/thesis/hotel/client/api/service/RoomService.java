package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.ReservedDateVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomReserveVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.RoomVo;

import java.util.List;

public interface RoomService {

    List<RoomVo> getRooms();

    RoomVo saveRoom(RoomVo roomVo);

    void addReservedDateToRoom(RoomVo roomVo, ReservedDateVo reservedDateVo);

    RoomVo getRoomById(Long id);

    RoomVo getRoomByNumber(int number);

}
