package hu.unideb.inf.thesis.hotel.service.mapper;

import hu.unideb.inf.thesis.hotel.client.api.vo.RoomTypeVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.RoomTypeEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RoomTypeMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoomTypeMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static RoomTypeVo toVo(RoomTypeEntity roomTypeEntity) {
        if (roomTypeEntity == null) {
            return null;
        }

        LOGGER.info(roomTypeEntity.getCapacity() + "");
        LOGGER.info("RoomType entity mapped to RoomTypeVo", roomTypeEntity);
        return mapper.map(roomTypeEntity, RoomTypeVo.class);
    }

    public static RoomTypeEntity toEntity(RoomTypeVo roomTypeVo) {
        if (roomTypeVo == null) {
            return null;
        }

        LOGGER.info("RoomTypeVo mapped to RoomType entity", roomTypeVo);
        return mapper.map(roomTypeVo, RoomTypeEntity.class);
    }

    public static void toEntity(RoomTypeVo roomTypeVo, RoomTypeEntity roomTypeEntity) {
        if (roomTypeVo == null || roomTypeEntity == null) {
            return;
        }
        mapper.map(roomTypeVo, roomTypeEntity);
    }

    public static List<RoomTypeVo> toVo(List<RoomTypeEntity> roomTypes) {
        return roomTypes.stream()
                .map(RoomTypeMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<RoomTypeEntity> toEntity(List<RoomTypeVo> roomTypeVos) {
        return roomTypeVos.stream()
                .map(RoomTypeMapper::toEntity)
                .collect(Collectors.toList());
    }

}
