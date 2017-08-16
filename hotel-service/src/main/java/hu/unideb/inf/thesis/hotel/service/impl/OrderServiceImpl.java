package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.OrderService;
import hu.unideb.inf.thesis.hotel.client.api.vo.OrderVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.OrderEntity;
import hu.unideb.inf.thesis.hotel.core.repository.OrderRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.OrderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;
import java.util.Date;

@Stateless(name = "OrderService", mappedName = "OrderService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(OrderService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
    private OrderRepository orderRepository;

    /*@Override
    public List<RoomVo> getRooms() {
        return RoomMapper.toVo(roomRepository.findAll());
    }*/

    @Override
    public OrderVo saveOrder(OrderVo orderVo) {
        OrderEntity orderEntity = orderRepository.findOne(orderVo.getId());

        if (orderEntity == null) {
            orderEntity = new OrderEntity();
            OrderMapper.toEntity(orderVo, orderEntity);
        }

        return OrderMapper.toVo(orderRepository.save(orderEntity));
    }

    @Override
    public OrderVo getOrderById(Long id) {
        return OrderMapper.toVo(orderRepository.findOne(id));
    }

}
