package hu.unideb.inf.thesis.hotel.service.mapper;

import hu.unideb.inf.thesis.hotel.client.api.vo.FoodTypeVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.FoodTypeEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class FoodTypeMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(FoodTypeMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static FoodTypeVo toVo(FoodTypeEntity foodTypeEntity) {
        if (foodTypeEntity == null) {
            return null;
        }

        LOGGER.info(foodTypeEntity.getName());
        LOGGER.info("FoodType entity mapped to FoodTypeVo", foodTypeEntity);
        return mapper.map(foodTypeEntity, FoodTypeVo.class);
    }

    public static FoodTypeEntity toEntity(FoodTypeVo foodTypeVo) {
        if (foodTypeVo == null) {
            return null;
        }

        LOGGER.info("FoodTypeVo mapped to FoodType entity", foodTypeVo);
        return mapper.map(foodTypeVo, FoodTypeEntity.class);
    }

    public static void toEntity(FoodTypeVo foodTypeVo, FoodTypeEntity foodTypeEntity) {
        if (foodTypeVo == null || foodTypeEntity == null) {
            return;
        }
        mapper.map(foodTypeVo, foodTypeEntity);
    }

    public static List<FoodTypeVo> toVo(List<FoodTypeEntity> foodTypes) {
        return foodTypes.stream()
                .map(FoodTypeMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<FoodTypeEntity> toEntity(List<FoodTypeVo> foodTypeVos) {
        return foodTypeVos.stream()
                .map(FoodTypeMapper::toEntity)
                .collect(Collectors.toList());
    }

}
