package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.FoodVo;

public interface FoodService {

    FoodVo saveFood(FoodVo foodVo);

    FoodVo getFoodById(Long id);

    FoodVo getFoodByName(String name);

    FoodVo getFoodByPrice(int price);

}
