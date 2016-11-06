package hu.unideb.inf.thesis.hotel.web.managedbeans.owner;

import hu.unideb.inf.thesis.hotel.client.api.service.DrinkService;
import hu.unideb.inf.thesis.hotel.client.api.service.FoodService;
import hu.unideb.inf.thesis.hotel.client.api.vo.DrinkVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.FoodVo;
import org.primefaces.event.RowEditEvent;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "ownerManagement")
public class OwnerManagementMB {

    @EJB
    private FoodService foodService;
    @EJB
    private DrinkService drinkService;

    public void onRowEditFood(RowEditEvent event) {
        foodService.saveFood((FoodVo) event.getObject());
    }

    public void onRowEditDrink(RowEditEvent event) {
        drinkService.saveDrink((DrinkVo) event.getObject());
    }

}
