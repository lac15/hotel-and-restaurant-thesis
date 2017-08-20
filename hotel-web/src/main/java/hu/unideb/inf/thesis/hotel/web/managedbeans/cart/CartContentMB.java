package hu.unideb.inf.thesis.hotel.web.managedbeans.cart;

import hu.unideb.inf.thesis.hotel.client.api.exception.EmailSendingException;
import hu.unideb.inf.thesis.hotel.client.api.service.MailService;
import hu.unideb.inf.thesis.hotel.client.api.service.UserService;
import hu.unideb.inf.thesis.hotel.client.api.vo.DrinkVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.FoodVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.UserVo;
import org.primefaces.context.RequestContext;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.jws.soap.SOAPBinding;

import java.util.*;

@ManagedBean(name = "cartContentBean")
@RequestScoped
public class CartContentMB {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CartContentMB.class);

    public static final int MAX_QUANTITY = 20;

    @ManagedProperty(value = "#{cartBean}")
    private CartMB cart;

    @EJB
    private UserService userService;
    @EJB
    private MailService mailService;

    private UserVo userVo;

    private List<Map.Entry<FoodVo, Integer>> foods;
    private List<Map.Entry<DrinkVo, Integer>> drinks;

    private int foodsTotal = 0;
    private int drinksTotal = 0;
    private int total = 0;

    @PostConstruct
    public void init() {
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        userVo = userService.getUserByUsername(username);

        foods = cart.getCart().getFoodsEntryList();
        drinks = cart.getCart().getDrinksEntryList();

        if (!cart.getCart().getFoodsQuantity().isEmpty()) {
            foodsTotal = cart.getCart().getFoodsTotalValue();
        }
        if (!cart.getCart().getDrinksQuantity().isEmpty()) {
            drinksTotal = cart.getCart().getDrinksTotalValue();
        }
        total = foodsTotal + drinksTotal;
    }

    public void plusFoodQuantity(FoodVo foodVo) {
        if (cart.getCart().getFoodsQuantity().get(foodVo) < MAX_QUANTITY) {
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
        if (cart.getCart().getDrinksQuantity().get(drinkVo) < MAX_QUANTITY) {
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

    public void saveOrder() {
        if (foods.isEmpty() && drinks.isEmpty()) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('emptyCartDialog').show();");
        }
        else {
            //save order to database
            //TODO

            sendOrderDetails();

            cart.getCart().getFoodsQuantity().clear();
            cart.getCart().getDrinksQuantity().clear();
            foods.clear();
            drinks.clear();
            foodsTotal = 0;
            drinksTotal = 0;
            total = 0;

            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('orderDialog').show();");
        }
    }

    public void sendOrderDetails() {
        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        String message = bundle.getString("email.roomreserve.dear") + " " + userVo.getFirstname() + " "
                + userVo.getLastname() + "!<br>";
        message += "<br>We have received your order.<br>";
        message += "<br>Ételek:<br><table width=\"400\" cellpadding=\"0\" cellspacing=\"0\" border=\"1\">";
        message += "<tr><th>Név</th><th>Darab</th><th>Ár</th></tr>";
        for (Map.Entry<FoodVo, Integer> foodEntry : cart.getCart().getFoodsEntryList()) {
            message += "<tr>";
            message += "<td width=\"33%\">" + foodEntry.getKey().getName() + "</td>";
            message += "<td width=\"33%\">" + foodEntry.getValue() + "</td>";
            message += "<td width=\"33%\">" + foodEntry.getKey().getPrice() * foodEntry.getValue() + "</td>";
            message += "</tr>";
        }
        message += "</table><br>";
        message += "<br>Italok:<br><table width=\"400\" cellpadding=\"0\" cellspacing=\"0\" border=\"1\">";
        message += "<tr><th>Név</th><th>Darab</th><th>Ár</th></tr>";
        for (Map.Entry<DrinkVo, Integer> drinkEntry : cart.getCart().getDrinksEntryList()) {
            message += "<tr>";
            message += "<td width=\"33%\">" + drinkEntry.getKey().getName() + "</td>";
            message += "<td width=\"33%\">" + drinkEntry.getValue() + "</td>";
            message += "<td width=\"33%\">" + drinkEntry.getKey().getPrice() * drinkEntry.getValue() + "</td>";
            message += "</tr>";
        }
        message += "</table><br>";
        message += "<br>Total: " + total + " Ft";

        try {
            mailService.sendMail("noreply@fourseasons.hu", userVo.getEmail(), bundle.getString("email.roomreserve.subject"), message);

            LOGGER.info(bundle.getString("email.logger.success"));
        } catch (EmailSendingException e) {
            LOGGER.info(bundle.getString("email.logger.error"));
            e.printStackTrace();
        }
    }

    public CartMB getCart() {
        return cart;
    }

    public void setCart(CartMB cart) {
        this.cart = cart;
    }

    public UserVo getUserVo() {
        return userVo;
    }

    public void setUserVo(UserVo userVo) {
        this.userVo = userVo;
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

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
