package hu.unideb.inf.thesis.hotel.web.managedbeans.cart;

import hu.unideb.inf.thesis.hotel.client.api.exception.EmailSendingException;
import hu.unideb.inf.thesis.hotel.client.api.service.*;
import hu.unideb.inf.thesis.hotel.client.api.vo.*;
import org.primefaces.context.RequestContext;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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
    private OrderedDrinkService orderedDrinkService;
    @EJB
    private OrderedFoodService orderedFoodService;
    @EJB
    private DrinkService drinkService;
    @EJB
    private FoodService foodService;
    @EJB
    private OrderService orderService;
    @EJB
    private MailService mailService;

    private UserVo userVo;
    private OrderedDrinkVo orderedDrinkVo = new OrderedDrinkVo();
    private OrderedFoodVo orderedFoodVo = new OrderedFoodVo();
    private OrderedDrinkVo orderedDrinkVoFromDb;
    private OrderedFoodVo orderedFoodVoFromDb;
    private OrderVo orderVo = new OrderVo();
    private OrderVo orderVoFromDb;

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

            foodsTotal = cart.getCart().getFoodsTotalValue();
            total = foodsTotal + drinksTotal;
        }
    }

    public void minusFoodQuantity(FoodVo foodVo) {
        if (cart.getCart().getFoodsQuantity().get(foodVo) == 1) {
            cart.getCart().getFoodsQuantity().remove(foodVo);
            foods = cart.getCart().getFoodsEntryList();
        }
        else {
            int newQuantity = cart.getCart().getFoodsQuantity().get(foodVo) - 1;

            cart.getCart().getFoodsQuantity().put(foodVo, newQuantity);
        }

        foodsTotal = cart.getCart().getFoodsTotalValue();
        total = foodsTotal + drinksTotal;
    }

    public void plusDrinkQuantity(DrinkVo drinkVo) {
        if (cart.getCart().getDrinksQuantity().get(drinkVo) < MAX_QUANTITY) {
            int newQuantity = cart.getCart().getDrinksQuantity().get(drinkVo) + 1;

            cart.getCart().getDrinksQuantity().put(drinkVo, newQuantity);

            drinksTotal = cart.getCart().getDrinksTotalValue();
            total = foodsTotal + drinksTotal;
        }
    }

    public void minusDrinkQuantity(DrinkVo drinkVo) {
        if (cart.getCart().getDrinksQuantity().get(drinkVo) == 1) {
            cart.getCart().getDrinksQuantity().remove(drinkVo);
            drinks = cart.getCart().getDrinksEntryList();
        }
        else {
            int newQuantity = cart.getCart().getDrinksQuantity().get(drinkVo) - 1;

            cart.getCart().getDrinksQuantity().put(drinkVo, newQuantity);
        }

        drinksTotal = cart.getCart().getDrinksTotalValue();
        total = foodsTotal + drinksTotal;
    }

    public void saveOrder() {
        if (foods.isEmpty() && drinks.isEmpty()) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("PF('emptyCartDialog').show();");
        }
        else {
            Date timestamp = new Date();
            orderVo.setTime(timestamp);
            orderVo.setTotalPrice(total);
            orderVoFromDb = orderService.saveOrder(orderVo);

            for (Map.Entry<DrinkVo, Integer> drinkEntry : cart.getCart().getDrinksEntryList()) {
                orderedDrinkVo.setQuantity(drinkEntry.getValue());
                orderedDrinkVoFromDb = orderedDrinkService.saveOrderedDrink(orderedDrinkVo);

                drinkService.addOrderedDrinkToDrink(drinkEntry.getKey(), orderedDrinkVoFromDb);

                orderService.addOrderedDrinkToOrder(orderVoFromDb, orderedDrinkVoFromDb);
            }
            for (Map.Entry<FoodVo, Integer> foodEntry : cart.getCart().getFoodsEntryList()) {
                orderedFoodVo.setQuantity(foodEntry.getValue());
                orderedFoodVoFromDb = orderedFoodService.saveOrderedFood(orderedFoodVo);

                foodService.addOrderedFoodToFood(foodEntry.getKey(), orderedFoodVoFromDb);

                orderService.addOrderedFoodToOrder(orderVoFromDb, orderedFoodVoFromDb);
            }

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

        String message = bundle.getString("email.order.dear") + " " + userVo.getFirstname() + " "
                + userVo.getLastname() + "!<br>";
        message += bundle.getString("email.order.message");
        message += bundle.getString("email.order.foods");
        message += "<table width=\"400\" cellpadding=\"0\" cellspacing=\"0\" border=\"1\">";
        message += "<tr><th>" + bundle.getString("email.order.name") + "</th><th>"
                + bundle.getString("email.order.quantity") + "</th><th>"
                + bundle.getString("email.order.price") + "</th></tr>";
        for (Map.Entry<FoodVo, Integer> foodEntry : cart.getCart().getFoodsEntryList()) {
            message += "<tr>";
            message += "<td width=\"33%\">" + foodEntry.getKey().getName() + "</td>";
            message += "<td width=\"33%\">" + foodEntry.getValue() + "</td>";
            message += "<td width=\"33%\">" + foodEntry.getKey().getPrice() * foodEntry.getValue() + "</td>";
            message += "</tr>";
        }
        message += "</table><br>";
        message += bundle.getString("email.order.drinks");
        message += "<table width=\"400\" cellpadding=\"0\" cellspacing=\"0\" border=\"1\">";
        message += "<tr><th>" + bundle.getString("email.order.name") + "</th><th>"
                + bundle.getString("email.order.quantity") + "</th><th>"
                + bundle.getString("email.order.price") + "</th></tr>";
        for (Map.Entry<DrinkVo, Integer> drinkEntry : cart.getCart().getDrinksEntryList()) {
            message += "<tr>";
            message += "<td width=\"33%\">" + drinkEntry.getKey().getName() + "</td>";
            message += "<td width=\"33%\">" + drinkEntry.getValue() + "</td>";
            message += "<td width=\"33%\">" + drinkEntry.getKey().getPrice() * drinkEntry.getValue() + "</td>";
            message += "</tr>";
        }
        message += "</table><br>";
        message += bundle.getString("email.order.total") + " " + total + " " + bundle.getString("email.order.huf");
        message += bundle.getString("email.order.endmessage");

        try {
            mailService.sendMail("noreply@fourseasons.hu", userVo.getEmail(), bundle.getString("email.order.subject"), message);

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

    public OrderedDrinkVo getOrderedDrinkVo() {
        return orderedDrinkVo;
    }

    public void setOrderedDrinkVo(OrderedDrinkVo orderedDrinkVo) {
        this.orderedDrinkVo = orderedDrinkVo;
    }

    public OrderedFoodVo getOrderedFoodVo() {
        return orderedFoodVo;
    }

    public void setOrderedFoodVo(OrderedFoodVo orderedFoodVo) {
        this.orderedFoodVo = orderedFoodVo;
    }

    public OrderedDrinkVo getOrderedDrinkVoFromDb() {
        return orderedDrinkVoFromDb;
    }

    public void setOrderedDrinkVoFromDb(OrderedDrinkVo orderedDrinkVoFromDb) {
        this.orderedDrinkVoFromDb = orderedDrinkVoFromDb;
    }

    public OrderedFoodVo getOrderedFoodVoFromDb() {
        return orderedFoodVoFromDb;
    }

    public void setOrderedFoodVoFromDb(OrderedFoodVo orderedFoodVoFromDb) {
        this.orderedFoodVoFromDb = orderedFoodVoFromDb;
    }

    public OrderVo getOrderVo() {
        return orderVo;
    }

    public void setOrderVo(OrderVo orderVo) {
        this.orderVo = orderVo;
    }

    public OrderVo getOrderVoFromDb() {
        return orderVoFromDb;
    }

    public void setOrderVoFromDb(OrderVo orderVoFromDb) {
        this.orderVoFromDb = orderVoFromDb;
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
