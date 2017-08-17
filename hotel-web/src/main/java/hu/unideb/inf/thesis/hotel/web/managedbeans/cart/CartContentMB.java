package hu.unideb.inf.thesis.hotel.web.managedbeans.cart;

import hu.unideb.inf.thesis.hotel.client.api.vo.CartVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.DrinkVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.FoodVo;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import java.util.*;

@ManagedBean(name = "cartContentBean")
@RequestScoped
public class CartContentMB {

    @ManagedProperty(value = "#{cartBean}")
    private CartMB cart;

    private List<Map.Entry<FoodVo, Integer>> foods;
    private List<Map.Entry<DrinkVo, Integer>> drinks;

    private int foodsTotal;
    private int drinksTotal;

    public FoodVo foodVo1 = new FoodVo(1L, "Bableves", 800);
    public FoodVo foodVo2 = new FoodVo(2L, "Húsleves", 1000);
    public DrinkVo drinkVo = new DrinkVo(1L, "Fanta", 300);;

    @PostConstruct
    public void init() {
        if (cart.getCart().getFoodsQuantity().isEmpty()) {
            cart.getCart().getFoodsQuantity().put(foodVo1, 1);
            cart.getCart().getFoodsQuantity().put(foodVo2, 2);
        }
        if (cart.getCart().getDrinksQuantity().isEmpty()) {
            cart.getCart().getDrinksQuantity().put(drinkVo, 3);
        }

        foods = getFoodsMap();
        foodsTotal = getFoodsTotalValue();
        drinks = getDrinksMap();
        drinksTotal = getDrinksTotalValue();
    }

    public List<Map.Entry<FoodVo, Integer>> getFoodsMap() {
        Set<Map.Entry<FoodVo, Integer>> foodsSet =
                cart.getCart().getFoodsQuantity().entrySet();
        return new ArrayList<Map.Entry<FoodVo, Integer>>(foodsSet);
    }

    public List<Map.Entry<DrinkVo, Integer>> getDrinksMap() {
        Set<Map.Entry<DrinkVo, Integer>> drinksSet =
                cart.getCart().getDrinksQuantity().entrySet();
        return new ArrayList<Map.Entry<DrinkVo, Integer>>(drinksSet);
    }

    public int getFoodsTotalValue() {
        int sum = 0;
        for (Map.Entry<FoodVo, Integer> entry : cart.getCart().getFoodsQuantity().entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        
        return sum;
    }

    public int getDrinksTotalValue() {
        int sum = 0;
        for (Map.Entry<DrinkVo, Integer> entry : cart.getCart().getDrinksQuantity().entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }

        return sum;
    }

    public CartMB getCart() {
        return cart;
    }

    public void setCart(CartMB cart) {
        this.cart = cart;
    }

    public List<Map.Entry<FoodVo, Integer>> getFoods() {
        return foods;
    }

    public void setFoods(List<Map.Entry<FoodVo, Integer>> foods) {
        this.foods = foods;
    }

    public List<Map.Entry<DrinkVo, Integer>> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<Map.Entry<DrinkVo, Integer>> drinks) {
        this.drinks = drinks;
    }

    public int getFoodsTotal() {
        return foodsTotal;
    }

    public void setFoodsTotal(int foodsTotal) {
        this.foodsTotal = foodsTotal;
    }

    public int getDrinksTotal() {
        return drinksTotal;
    }

    public void setDrinksTotal(int drinksTotal) {
        this.drinksTotal = drinksTotal;
    }
}
