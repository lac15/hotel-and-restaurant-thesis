package hu.unideb.inf.thesis.hotel.service.mapper;

import hu.unideb.inf.thesis.hotel.client.api.vo.OrderedFoodVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.OrderedFoodEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OrderedFoodMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedFoodMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static OrderedFoodVo toVo(OrderedFoodEntity orderedFoodEntity) {
        if (orderedFoodEntity == null) {
            return null;
        }

        LOGGER.info(orderedFoodEntity.getQuantity() + "");
        LOGGER.info("OrderedFood entity mapped to OrderedFoodVo", orderedFoodEntity);
        return mapper.map(orderedFoodEntity, OrderedFoodVo.class);
    }

    public static OrderedFoodEntity toEntity(OrderedFoodVo orderedFoodVo) {
        if (orderedFoodVo == null) {
            return null;
        }

        LOGGER.info("OrderedFoodVo mapped to OrderedFood entity", orderedFoodVo);
        return mapper.map(orderedFoodVo, OrderedFoodEntity.class);
    }

    public static void toEntity(OrderedFoodVo orderedFoodVo, OrderedFoodEntity orderedFoodEntity) {
        if (orderedFoodVo == null || orderedFoodEntity == null) {
            return;
        }
        mapper.map(orderedFoodVo, orderedFoodEntity);
    }

    public static List<OrderedFoodVo> toVo(List<OrderedFoodEntity> orderedFoods) {
        return orderedFoods.stream()
                .map(OrderedFoodMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<OrderedFoodEntity> toEntity(List<OrderedFoodVo> orderedFoodVos) {
        return orderedFoodVos.stream()
                .map(OrderedFoodMapper::toEntity)
                .collect(Collectors.toList());
    }

}
