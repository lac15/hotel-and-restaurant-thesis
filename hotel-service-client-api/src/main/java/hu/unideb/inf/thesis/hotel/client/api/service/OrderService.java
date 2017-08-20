package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.*;

public interface OrderService {

    OrderVo saveOrder(OrderVo orderVo);

    OrderVo getOrderById(Long id);

    void addOrderedDrinkToOrder(OrderVo orderVo, OrderedDrinkVo orderedDrinkVo);

    void addOrderedFoodToOrder(OrderVo orderVo, OrderedFoodVo orderedFoodVo);

}
