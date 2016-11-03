package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.FoodTypeVo;

public interface FoodTypeService {

    FoodTypeVo saveFoodType(FoodTypeVo foodTypeVo);

    FoodTypeVo getFoodTypeById(Long id);

    FoodTypeVo getFoodTypeByName(String name);

}
