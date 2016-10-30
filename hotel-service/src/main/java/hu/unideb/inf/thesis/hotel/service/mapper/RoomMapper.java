package hu.unideb.inf.thesis.hotel.service.mapper;

import hu.unideb.inf.thesis.hotel.client.api.vo.RoomVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.RoomEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RoomMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static RoomVo toVo(RoomEntity roomEntity) {
        if (roomEntity == null) {
            return null;
        }

        LOGGER.info(roomEntity.getNumber() + "");
        LOGGER.info("Room entity mapped to RoomVo", roomEntity);
        return mapper.map(roomEntity, RoomVo.class);
    }

    public static RoomEntity toEntity(RoomVo roomVo) {
        if (roomVo == null) {
            return null;
        }

        LOGGER.info("RoomVo mapped to Room entity", roomVo);
        return mapper.map(roomVo, RoomEntity.class);
    }

    public static void toEntity(RoomVo roomVo, RoomEntity roomEntity) {
        if (roomVo == null || roomEntity == null) {
            return;
        }
        mapper.map(roomVo, roomEntity);
    }

    public static List<RoomVo> toVo(List<RoomEntity> rooms) {
        return rooms.stream()
                .map(RoomMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<RoomEntity> toEntity(List<RoomVo> roomVos) {
        return roomVos.stream()
                .map(RoomMapper::toEntity)
                .collect(Collectors.toList());
    }

}
