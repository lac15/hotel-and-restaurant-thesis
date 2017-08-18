package hu.unideb.inf.thesis.hotel.web.managedbeans.menu;

import hu.unideb.inf.thesis.hotel.client.api.service.DrinkService;
import hu.unideb.inf.thesis.hotel.client.api.vo.DrinkVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "drinksMB")
public class DrinksMB {

    @EJB
    private DrinkService drinkService;

    private List<DrinkVo> drinks = new ArrayList<DrinkVo>();
    private List<DrinkVo> selectedDrinks = new ArrayList<DrinkVo>();
    private DrinkVo drink;

    private DrinkVo drinkVo;
    private int quantity = 1;

    @PostConstruct
    public void init() {
        drinks.addAll(drinkService.getDrinks());
    }

    public void addDrinkVo(DrinkVo drink) {
        drinkVo = drink;
    }

    public void addToCart() {
        //TODO

        quantity = 1;
    }

    public List<DrinkVo> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkVo> drinks) {
        this.drinks = drinks;
    }

    public List<DrinkVo> getSelectedDrinks() {
        return selectedDrinks;
    }

    public void setSelectedDrinks(List<DrinkVo> selectedDrinks) {
        this.selectedDrinks = selectedDrinks;
    }

    public DrinkVo getDrink() {
        return drink;
    }

    public void setDrink(DrinkVo drink) {
        this.drink = drink;
    }

    public DrinkVo getDrinkVo() {
        return drinkVo;
    }

    public void setDrinkVo(DrinkVo drinkVo) {
        this.drinkVo = drinkVo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
