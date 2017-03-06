package hu.unideb.inf.thesis.hotel.service.mapper;

import hu.unideb.inf.thesis.hotel.client.api.vo.OrderVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.OrderEntity;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class OrderMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderMapper.class);

    private static Mapper mapper = new DozerBeanMapper();

    public static OrderVo toVo(OrderEntity orderEntity) {
        if (orderEntity == null) {
            return null;
        }

        LOGGER.info(orderEntity.getTime() + "");
        LOGGER.info("Order entity mapped to OrderVo", orderEntity);
        return mapper.map(orderEntity, OrderVo.class);
    }

    public static OrderEntity toEntity(OrderVo orderVo) {
        if (orderVo == null) {
            return null;
        }

        LOGGER.info("OrderVo mapped to Order entity", orderVo);
        return mapper.map(orderVo, OrderEntity.class);
    }

    public static void toEntity(OrderVo orderVo, OrderEntity orderEntity) {
        if (orderVo == null || orderEntity == null) {
            return;
        }
        mapper.map(orderVo, orderEntity);
    }

    public static List<OrderVo> toVo(List<OrderEntity> orders) {
        return orders.stream()
                .map(OrderMapper::toVo)
                .collect(Collectors.toList());
    }

    public static List<OrderEntity> toEntity(List<OrderVo> orderVos) {
        return orderVos.stream()
                .map(OrderMapper::toEntity)
                .collect(Collectors.toList());
    }

}
