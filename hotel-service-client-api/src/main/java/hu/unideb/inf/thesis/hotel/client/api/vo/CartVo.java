package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;
import java.util.*;

public class CartVo implements Serializable {

    private static final long serialVersionUID = 1174930328505763772L;

    private Map<FoodVo, Integer> foodsQuantity = new HashMap<FoodVo, Integer>();

    private Map<DrinkVo, Integer> drinksQuantity = new HashMap<DrinkVo, Integer>();

    public CartVo(){}

    public List<Map.Entry<FoodVo, Integer>> getFoodsEntryList() {
        Set<Map.Entry<FoodVo, Integer>> foodsSet = foodsQuantity.entrySet();
        return new ArrayList<Map.Entry<FoodVo, Integer>>(foodsSet);
    }

    public List<Map.Entry<DrinkVo, Integer>> getDrinksEntryList() {
        Set<Map.Entry<DrinkVo, Integer>> drinksSet = drinksQuantity.entrySet();
        return new ArrayList<Map.Entry<DrinkVo, Integer>>(drinksSet);
    }

    public int getFoodsTotalValue() {
        int sum = 0;
        for (Map.Entry<FoodVo, Integer> entry : foodsQuantity.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }

    public int getDrinksTotalValue() {
        int sum = 0;
        for (Map.Entry<DrinkVo, Integer> entry : drinksQuantity.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }

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
