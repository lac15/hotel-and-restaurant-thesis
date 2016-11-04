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
    private String drink;

    @PostConstruct
    public void init() {
        drinks.addAll(drinkService.getDrinks());
    }

    public List<DrinkVo> getDrinks() {
        return drinks;
    }

    public void setDrinks(List<DrinkVo> drinks) {
        this.drinks = drinks;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }
}