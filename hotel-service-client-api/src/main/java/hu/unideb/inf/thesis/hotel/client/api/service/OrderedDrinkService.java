package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.OrderedDrinkVo;

public interface OrderedDrinkService {

    OrderedDrinkVo saveOrderedDrink(OrderedDrinkVo orderedDrinkVo);

    OrderedDrinkVo getOrderedDrinkById(Long id);

    OrderedDrinkVo getOrderedDrinkByQuantity(int quantity);
}
