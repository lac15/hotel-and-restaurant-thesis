package hu.unideb.inf.thesis.hotel.web.managedbeans.cart;

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

    private int foodsTotal = 0;
    private int drinksTotal = 0;

    public FoodVo foodVo1 = new FoodVo(1L, "Bableves", 800);
    public FoodVo foodVo2 = new FoodVo(2L, "Húsleves", 1000);
    public DrinkVo drinkVo = new DrinkVo(1L, "Fanta", 300);;

    @PostConstruct
    public void init() {
        foods = cart.getCart().getFoodsEntryList();
        drinks = cart.getCart().getDrinksEntryList();

        if (!cart.getCart().getFoodsQuantity().isEmpty()) {
            foodsTotal = cart.getCart().getFoodsTotalValue();
        }
        if (!cart.getCart().getDrinksQuantity().isEmpty()) {
            drinksTotal = cart.getCart().getDrinksTotalValue();
        }
    }

    public void plusFoodQuantity(FoodVo foodVo) {
        if (cart.getCart().getFoodsQuantity().get(foodVo) < 20) {
            int newQuantity = cart.getCart().getFoodsQuantity().get(foodVo) + 1;

            cart.getCart().getFoodsQuantity().put(foodVo, newQuantity);
        }
    }

    public void minusFoodQuantity(FoodVo foodVo) {
        if (cart.getCart().getFoodsQuantity().get(foodVo) == 1) {
            cart.getCart().getFoodsQuantity().remove(foodVo);
        }
        else {
            int newQuantity = cart.getCart().getFoodsQuantity().get(foodVo) - 1;

            cart.getCart().getFoodsQuantity().put(foodVo, newQuantity);
        }
    }

    public void plusDrinkQuantity(DrinkVo drinkVo) {
        if (cart.getCart().getDrinksQuantity().get(drinkVo) < 20) {
            int newQuantity = cart.getCart().getDrinksQuantity().get(drinkVo) + 1;

            cart.getCart().getDrinksQuantity().put(drinkVo, newQuantity);
        }
    }

    public void minusDrinkQuantity(DrinkVo drinkVo) {
        if (cart.getCart().getDrinksQuantity().get(drinkVo) == 1) {
            cart.getCart().getDrinksQuantity().remove(drinkVo);
        }
        else {
            int newQuantity = cart.getCart().getDrinksQuantity().get(drinkVo) - 1;

            cart.getCart().getDrinksQuantity().put(drinkVo, newQuantity);
        }
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
