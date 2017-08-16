package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.OrderVo;

import java.util.Date;

public interface OrderService {

    OrderVo saveOrder(OrderVo orderVo);

    OrderVo getOrderById(Long id);

}
