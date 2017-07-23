package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.RoomTypeVo;

import java.util.List;

public interface RoomTypeService {

    List<RoomTypeVo> getRoomTypes();

    RoomTypeVo saveRoomType(RoomTypeVo roomTypeVo);

    RoomTypeVo getRoomTypeById(Long id);

    RoomTypeVo getRoomTypeByCapacity(int capacity);

}
