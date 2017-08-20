package hu.unideb.inf.thesis.hotel.web.managedbeans.menu;

import hu.unideb.inf.thesis.hotel.client.api.service.FoodService;
import hu.unideb.inf.thesis.hotel.client.api.vo.FoodVo;
import hu.unideb.inf.thesis.hotel.web.managedbeans.cart.CartMB;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "foodsMB")
@ViewScoped
public class FoodsMB implements Serializable {

    private static final long serialVersionUID = 4722533328505763772L;
    private static final int MAX_QUANTITY = 20;

    @ManagedProperty(value = "#{cartBean}")
    private CartMB cart;

    @EJB
    private FoodService foodService;

    private List<FoodVo> soups = new ArrayList<FoodVo>();
    private List<FoodVo> mainCourses = new ArrayList<FoodVo>();
    private List<FoodVo> desserts = new ArrayList<FoodVo>();
    private List<FoodVo> selectedSoups = new ArrayList<FoodVo>();
    private List<FoodVo> selectedMainCourses = new ArrayList<FoodVo>();
    private List<FoodVo> selectedDesserts = new ArrayList<FoodVo>();
    private FoodVo soup;
    private FoodVo mainCourse;
    private FoodVo dessert;

    private FoodVo foodVo;
    private int quantity = 1;

    @PostConstruct
    public void init() {
        soups.addAll(foodService.getFoodByTypeName("Soup"));
        mainCourses.addAll(foodService.getFoodByTypeName("Main course"));
        desserts.addAll(foodService.getFoodByTypeName("Dessert"));
    }

    public void addFoodVo(FoodVo food) {
        foodVo = food;
    }

    public void addFoodToCart() {
        if (cart.getCart().getFoodsQuantity().containsKey(foodVo)) {
            int quantitySum = cart.getCart().getFoodsQuantity().get(foodVo) + quantity;

            if (quantitySum > MAX_QUANTITY) {
                cart.getCart().getFoodsQuantity().put(foodVo, MAX_QUANTITY);
            }
            else {
                cart.getCart().getFoodsQuantity().put(foodVo, quantitySum);
            }
        }
        else {
            cart.getCart().getFoodsQuantity().put(foodVo, quantity);
        }
        quantity = 1;
    }

    public CartMB getCart() {
        return cart;
    }

    public void setCart(CartMB cart) {
        this.cart = cart;
    }

    public List<FoodVo> getSoups() {
        return soups;
    }

    public void setSoups(List<FoodVo> soups) {
        this.soups = soups;
    }

    public List<FoodVo> getMainCourses() {
        return mainCourses;
    }

    public void setMainCourses(List<FoodVo> mainCourses) {
        this.mainCourses = mainCourses;
    }

    public List<FoodVo> getDesserts() {
        return desserts;
    }

    public void setDesserts(List<FoodVo> desserts) {
        this.desserts = desserts;
    }

    public List<FoodVo> getSelectedSoups() {
        return selectedSoups;
    }

    public void setSelectedSoups(List<FoodVo> selectedSoups) {
        this.selectedSoups = selectedSoups;
    }

    public List<FoodVo> getSelectedMainCourses() {
        return selectedMainCourses;
    }

    public void setSelectedMainCourses(List<FoodVo> selectedMainCourses) {
        this.selectedMainCourses = selectedMainCourses;
    }

    public List<FoodVo> getSelectedDesserts() {
        return selectedDesserts;
    }

    public void setSelectedDesserts(List<FoodVo> selectedDesserts) {
        this.selectedDesserts = selectedDesserts;
    }

    public FoodVo getSoup() {
        return soup;
    }

    public void setSoup(FoodVo soup) {
        this.soup = soup;
    }

    public FoodVo getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(FoodVo mainCourse) {
        this.mainCourse = mainCourse;
    }

    public FoodVo getDessert() {
        return dessert;
    }

    public void setDessert(FoodVo dessert) {
        this.dessert = dessert;
    }

    public FoodVo getFoodVo() {
        return foodVo;
    }

    public void setFoodVo(FoodVo foodVo) {
        this.foodVo = foodVo;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
