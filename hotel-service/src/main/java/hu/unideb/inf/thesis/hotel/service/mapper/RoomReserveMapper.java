package hu.unideb.inf.thesis.hotel.service.mapper;

import hu.unideb.inf.thesis.hotel.client.api.vo.RoomReserveVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.RoomReserveEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RoomReserveMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomReserveMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static RoomReserveVo toVo(RoomReserveEntity roomReserveEntity) {
        if (roomReserveEntity == null) {
            return null;
        }

        LOGGER.info(roomReserveEntity.getRoom().getNumber() + "");
        LOGGER.info("RoomReserve entity mapped to RoomReserveVo", roomReserveEntity);
        return mapper.map(roomReserveEntity, RoomReserveVo.class);
    }

    public static RoomReserveEntity toEntity(RoomReserveVo roomReserveVo) {
        if (roomReserveVo == null) {
            return null;
        }

        LOGGER.info("RoomReserveVo mapped to RoomReserve entity", roomReserveVo);
        return mapper.map(roomReserveVo, RoomReserveEntity.class);
    }

    public static void toEntity(RoomReserveVo roomReserveVo, RoomReserveEntity roomReserveEntity) {
        if (roomReserveVo == null || roomReserveEntity == null) {
            return;
        }
        mapper.map(roomReserveVo, roomReserveEntity);
    }

    public static List<RoomReserveVo> toVo(List<RoomReserveEntity> roomReserves) {
        return roomReserves.stream()
                .map(RoomReserveMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<RoomReserveEntity> toEntity(List<RoomReserveVo> roomReserveVos) {
        return roomReserveVos.stream()
                .map(RoomReserveMapper::toEntity)
                .collect(Collectors.toList());
    }

}
