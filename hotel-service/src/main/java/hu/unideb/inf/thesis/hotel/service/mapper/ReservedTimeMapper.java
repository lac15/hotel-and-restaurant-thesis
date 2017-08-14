package hu.unideb.inf.thesis.hotel.service.mapper;

import hu.unideb.inf.thesis.hotel.client.api.vo.ReservedTimeVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.ReservedTimeEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ReservedTimeMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservedTimeMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static ReservedTimeVo toVo(ReservedTimeEntity reservedTimeEntity) {
        if (reservedTimeEntity == null) {
            return null;
        }

        LOGGER.info(reservedTimeEntity.getReservedTime() + "");
        LOGGER.info("ReservedTime entity mapped to ReservedTimeVo", reservedTimeEntity);
        return mapper.map(reservedTimeEntity, ReservedTimeVo.class);
    }

    public static ReservedTimeEntity toEntity(ReservedTimeVo reservedTimeVo) {
        if (reservedTimeVo == null) {
            return null;
        }

        LOGGER.info("ReservedTimeVo mapped to ReservedTime entity", reservedTimeVo);
        return mapper.map(reservedTimeVo, ReservedTimeEntity.class);
    }

    public static void toEntity(ReservedTimeVo reservedTimeVo, ReservedTimeEntity reservedTimeEntity) {
        if (reservedTimeVo == null || reservedTimeEntity == null) {
            return;
        }
        mapper.map(reservedTimeVo, reservedTimeEntity);
    }

    public static List<ReservedTimeVo> toVo(List<ReservedTimeEntity> reservedTimes) {
        return reservedTimes.stream()
                .map(ReservedTimeMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<ReservedTimeEntity> toEntity(List<ReservedTimeVo> reservedTimeVos) {
        return reservedTimeVos.stream()
                .map(ReservedTimeMapper::toEntity)
                .collect(Collectors.toList());
    }

}
