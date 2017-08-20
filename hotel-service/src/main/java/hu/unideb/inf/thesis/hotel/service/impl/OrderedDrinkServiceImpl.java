package hu.unideb.inf.thesis.hotel.service.impl;

import hu.unideb.inf.thesis.hotel.client.api.service.OrderedDrinkService;
import hu.unideb.inf.thesis.hotel.client.api.vo.OrderedDrinkVo;
import hu.unideb.inf.thesis.hotel.core.entitiy.OrderedDrinkEntity;
import hu.unideb.inf.thesis.hotel.core.repository.OrderedDrinkRepository;
import hu.unideb.inf.thesis.hotel.service.mapper.OrderedDrinkMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;

import javax.ejb.*;
import javax.interceptor.Interceptors;

@Stateless(name = "OrderedDrinkService", mappedName = "OrderedDrinkService")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@Local(OrderedDrinkService.class)
@Interceptors({SpringBeanAutowiringInterceptor.class})
public class OrderedDrinkServiceImpl implements OrderedDrinkService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderedDrinkServiceImpl.class);

    @Autowired
    private OrderedDrinkRepository orderedDrinkRepository;

    @Override
    public void saveOrderedDrink(OrderedDrinkVo orderedDrinkVo) {
        OrderedDrinkEntity orderedDrinkEntity = new OrderedDrinkEntity();
        OrderedDrinkMapper.toEntity(orderedDrinkVo, orderedDrinkEntity);
        orderedDrinkRepository.save(orderedDrinkEntity);
    }

    @Override
    public OrderedDrinkVo getOrderedDrinkById(Long id) {
        return OrderedDrinkMapper.toVo(orderedDrinkRepository.findOne(id));
    }

    @Override
    public OrderedDrinkVo getOrderedDrinkByQuantity(int quantity) {
        return OrderedDrinkMapper.toVo(orderedDrinkRepository.findByQuantity(quantity));
    }
}
