package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CartVo implements Serializable {

    private static final long serialVersionUID = 1174930328505763772L;

    private Map<FoodVo, Integer> foodsQuantity = new HashMap<FoodVo, Integer>();

    private Map<DrinkVo, Integer> drinksQuantity = new HashMap<DrinkVo, Integer>();

    public CartVo(){}

    public Map<FoodVo, Integer> getFoodsQuantity() {
        return foodsQuantity;
    }

    public void setFoodsQuantity(Map<FoodVo, Integer> foodsQuantity) {
        this.foodsQuantity = foodsQuantity;
    }

    public Map<DrinkVo, Integer> getDrinksQuantity() {
        return drinksQuantity;
    }

    public void setDrinksQuantity(Map<DrinkVo, Integer> drinksQuantity) {
        this.drinksQuantity = drinksQuantity;
    }
}
