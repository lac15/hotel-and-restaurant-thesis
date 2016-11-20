package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.FoodVo;

import java.util.List;

public interface FoodService {

    void saveFood(FoodVo foodVo);

    void addFoodToFoodType(FoodVo foodVo, String typeName);

    void deleteFood(Long id, String typeName);

    FoodVo getFoodById(Long id);

    FoodVo getFoodByName(String name);

    FoodVo getFoodByPrice(int price);

    List<FoodVo> getFoodByTypeName(String typeName);

    List<FoodVo> getFoods();
}
