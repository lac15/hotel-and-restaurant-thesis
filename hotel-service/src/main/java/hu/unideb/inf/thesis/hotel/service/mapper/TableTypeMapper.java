package hu.unideb.inf.thesis.hotel.service.mapper;

import hu.unideb.inf.thesis.hotel.client.api.vo.TableTypeVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.TableTypeEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class TableTypeMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(TableTypeMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static TableTypeVo toVo(TableTypeEntity tableTypeEntity) {
        if (tableTypeEntity == null) {
            return null;
        }

        LOGGER.info(tableTypeEntity.getSeats() + "");
        LOGGER.info("TableType entity mapped to TableTypeVo", tableTypeEntity);
        return mapper.map(tableTypeEntity, TableTypeVo.class);
    }

    public static TableTypeEntity toEntity(TableTypeVo tableTypeVo) {
        if (tableTypeVo == null) {
            return null;
        }

        LOGGER.info("TableTypeVo mapped to TableType entity", tableTypeVo);
        return mapper.map(tableTypeVo, TableTypeEntity.class);
    }

    public static void toEntity(TableTypeVo tableTypeVo, TableTypeEntity tableTypeEntity) {
        if (tableTypeVo == null || tableTypeEntity == null) {
            return;
        }
        mapper.map(tableTypeVo, tableTypeEntity);
    }

    public static List<TableTypeVo> toVo(List<TableTypeEntity> tableTypes) {
        return tableTypes.stream()
                .map(TableTypeMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<TableTypeEntity> toEntity(List<TableTypeVo> tableTypeVos) {
        return tableTypeVos.stream()
                .map(TableTypeMapper::toEntity)
                .collect(Collectors.toList());
    }

}
