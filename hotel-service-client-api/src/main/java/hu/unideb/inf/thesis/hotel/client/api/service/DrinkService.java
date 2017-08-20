package hu.unideb.inf.thesis.hotel.client.api.service;

import hu.unideb.inf.thesis.hotel.client.api.vo.DrinkVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.OrderedDrinkVo;

import java.util.List;

public interface DrinkService {

    void saveDrink(DrinkVo drinkVo);

    void deleteDrink(Long id);

    void addOrderedDrinkToDrink(DrinkVo drinkVo, OrderedDrinkVo orderedDrinkVo);

    DrinkVo getDrinkById(Long id);

    DrinkVo getDrinkByName(String name);

    DrinkVo getDrinkByPrice(int price);

    List<DrinkVo> getDrinks();

}
