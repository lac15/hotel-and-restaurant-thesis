package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.RoomVo;

public interface RoomService {

    RoomVo saveRoom(RoomVo roomVo);

    RoomVo getRoomById(Long id);

    RoomVo getRoomByNumber(int number);

    void setRoomReservedByNumber(int number, boolean reserved);

}
