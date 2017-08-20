package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.OrderedFoodService;
import hu.unideb.inf.thesis.hotel.client.api.vo.OrderedFoodVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.OrderedFoodEntity;
import hu.unideb.inf.thesis.hotel.core.repository.OrderedFoodRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.OrderedFoodMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;

@Stateless(name = "OrderedFoodService", mappedName = "OrderedFoodService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(OrderedFoodService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class OrderedFoodServiceImpl implements OrderedFoodService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedFoodServiceImpl.class);

    @Autowired
    private OrderedFoodRepository orderedFoodRepository;

    @Override
    public void saveOrderedFood(OrderedFoodVo orderedFoodVo) {
        OrderedFoodEntity orderedFoodEntity = new OrderedFoodEntity();
        OrderedFoodMapper.toEntity(orderedFoodVo, orderedFoodEntity);
        orderedFoodRepository.save(orderedFoodEntity);
    }

    @Override
    public OrderedFoodVo getOrderedFoodById(Long id) {
        return OrderedFoodMapper.toVo(orderedFoodRepository.findOne(id));
    }

    @Override
    public OrderedFoodVo getOrderedFoodByQuantity(int quantity) {
        return OrderedFoodMapper.toVo(orderedFoodRepository.findByQuantity(quantity));
    }
}
