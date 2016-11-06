package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.FoodVo;

import java.util.List;

public interface FoodService {

    FoodVo saveFood(FoodVo foodVo);

    void deleteFood(Long id);

    FoodVo getFoodById(Long id);

    FoodVo getFoodByName(String name);

    FoodVo getFoodByPrice(int price);

    List<FoodVo> getFoodByTypeName(String typeName);

    List<FoodVo> getFoods();
}
