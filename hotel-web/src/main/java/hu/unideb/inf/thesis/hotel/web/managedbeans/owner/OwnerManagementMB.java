package hu.unideb.inf.thesis.hotel.web.managedbeans.owner;

import hu.unideb.inf.thesis.hotel.client.api.service.DrinkService;
import hu.unideb.inf.thesis.hotel.client.api.service.FoodService;
import hu.unideb.inf.thesis.hotel.client.api.service.FoodTypeService;
import hu.unideb.inf.thesis.hotel.client.api.vo.DrinkVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.FoodTypeVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.FoodVo;
import org.primefaces.event.RowEditEvent;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "ownerManagement")
public class OwnerManagementMB {

    @EJB
    private FoodService foodService;
    @EJB
    private FoodTypeService foodTypeService;
    @EJB
    private DrinkService drinkService;

    public void onRowEditFood(RowEditEvent event) {
        foodService.saveFood((FoodVo) event.getObject());
    }

    public void onRowEditDrink(RowEditEvent event) {
        drinkService.saveDrink((DrinkVo) event.getObject());
    }

    public void addSoup(){

    }

    public void addMainCourse(){

    }

    public void addDessert(){

    }

    public void addDrink(){

    }

    public void deleteSoup(List<FoodVo> soups){
        for (FoodVo soup : soups) {
            foodService.deleteFood(soup.getId(), "Soup");
        }
    }

    public void deleteMainCourse(List<FoodVo> mainCourses){
        for (FoodVo mainCourse : mainCourses) {
            foodService.deleteFood(mainCourse.getId(), "Main course");
        }
    }

    public void deleteDessert(List<FoodVo> desserts){
        for (FoodVo dessert : desserts) {
            foodService.deleteFood(dessert.getId(), "Dessert");
        }
    }

    public void deleteDrink(List<DrinkVo> drinks){
        for (DrinkVo drink : drinks) {
            drinkService.deleteDrink(drink.getId());
        }
    }

}
