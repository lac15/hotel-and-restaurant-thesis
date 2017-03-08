package hu.unideb.inf.thesis.hotel.service.mapper;

import hu.unideb.inf.thesis.hotel.client.api.vo.TableReserveVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.TableReserveEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TableReserveMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableReserveMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static TableReserveVo toVo(TableReserveEntity tableReserveEntity) {
        if (tableReserveEntity == null) {
            return null;
        }

        LOGGER.info(tableReserveEntity.getTable().getNumber() + "");
        LOGGER.info("TableReserve entity mapped to TableReserveVo", tableReserveEntity);
        return mapper.map(tableReserveEntity, TableReserveVo.class);
    }

    public static TableReserveEntity toEntity(TableReserveVo tableReserveVo) {
        if (tableReserveVo == null) {
            return null;
        }

        LOGGER.info("TableReserveVo mapped to TableReserve entity", tableReserveVo);
        return mapper.map(tableReserveVo, TableReserveEntity.class);
    }

    public static void toEntity(TableReserveVo tableReserveVo, TableReserveEntity tableReserveEntity) {
        if (tableReserveVo == null || tableReserveEntity == null) {
            return;
        }
        mapper.map(tableReserveVo, tableReserveEntity);
    }

    public static List<TableReserveVo> toVo(List<TableReserveEntity> tableReserves) {
        return tableReserves.stream()
                .map(TableReserveMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<TableReserveEntity> toEntity(List<TableReserveVo> tableReserveVos) {
        return tableReserveVos.stream()
                .map(TableReserveMapper::toEntity)
                .collect(Collectors.toList());
    }

}
