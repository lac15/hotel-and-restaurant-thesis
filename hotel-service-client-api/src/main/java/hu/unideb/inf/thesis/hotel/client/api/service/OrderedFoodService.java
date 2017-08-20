package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.OrderedFoodVo;

public interface OrderedFoodService {

    void saveOrderedFood(OrderedFoodVo orderedFoodVo);

    OrderedFoodVo getOrderedFoodById(Long id);

    OrderedFoodVo getOrderedFoodByQuantity(int quantity);
}
