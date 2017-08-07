package hu.unideb.inf.thesis.hotel.service.mapper;

import hu.unideb.inf.thesis.hotel.client.api.vo.ReservedDateVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.ReservedDateEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class ReservedDateMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReservedDateMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static ReservedDateVo toVo(ReservedDateEntity reservedDateEntity) {
        if (reservedDateEntity == null) {
            return null;
        }

        LOGGER.info(reservedDateEntity.getReservedDate() + "");
        LOGGER.info("ReservedDate entity mapped to ReservedDateVo", reservedDateEntity);
        return mapper.map(reservedDateEntity, ReservedDateVo.class);
    }

    public static ReservedDateEntity toEntity(ReservedDateVo reservedDateVo) {
        if (reservedDateVo == null) {
            return null;
        }

        LOGGER.info("ReservedDateVo mapped to ReservedDate entity", reservedDateVo);
        return mapper.map(reservedDateVo, ReservedDateEntity.class);
    }

    public static void toEntity(ReservedDateVo reservedDateVo, ReservedDateEntity reservedDateEntity) {
        if (reservedDateVo == null || reservedDateEntity == null) {
            return;
        }
        mapper.map(reservedDateVo, reservedDateEntity);
    }

    public static List<ReservedDateVo> toVo(List<ReservedDateEntity> reservedDates) {
        return reservedDates.stream()
                .map(ReservedDateMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<ReservedDateEntity> toEntity(List<ReservedDateVo> reservedDateVos) {
        return reservedDateVos.stream()
                .map(ReservedDateMapper::toEntity)
                .collect(Collectors.toList());
    }

}
