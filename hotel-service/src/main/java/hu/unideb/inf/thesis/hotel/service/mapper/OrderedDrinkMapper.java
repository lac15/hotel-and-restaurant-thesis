package hu.unideb.inf.thesis.hotel.service.mapper;

import hu.unideb.inf.thesis.hotel.client.api.vo.OrderedDrinkVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.OrderedDrinkEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OrderedDrinkMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedDrinkMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static OrderedDrinkVo toVo(OrderedDrinkEntity orderedDrinkEntity) {
        if (orderedDrinkEntity == null) {
            return null;
        }

        LOGGER.info(orderedDrinkEntity.getQuantity() + "");
        LOGGER.info("OrderedDrink entity mapped to OrderedDrinkVo", orderedDrinkEntity);
        return mapper.map(orderedDrinkEntity, OrderedDrinkVo.class);
    }

    public static OrderedDrinkEntity toEntity(OrderedDrinkVo orderedDrinkVo) {
        if (orderedDrinkVo == null) {
            return null;
        }

        LOGGER.info("OrderedFoodVo mapped to OrderedFood entity", orderedDrinkVo);
        return mapper.map(orderedDrinkVo, OrderedDrinkEntity.class);
    }

    public static void toEntity(OrderedDrinkVo orderedDrinkVo, OrderedDrinkEntity orderedDrinkEntity) {
        if (orderedDrinkVo == null || orderedDrinkEntity == null) {
            return;
        }
        mapper.map(orderedDrinkVo, orderedDrinkEntity);
    }

    public static List<OrderedDrinkVo> toVo(List<OrderedDrinkEntity> orderedDrinks) {
        return orderedDrinks.stream()
                .map(OrderedDrinkMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<OrderedDrinkEntity> toEntity(List<OrderedDrinkVo> orderedDrinkVos) {
        return orderedDrinkVos.stream()
                .map(OrderedDrinkMapper::toEntity)
                .collect(Collectors.toList());
    }

}
