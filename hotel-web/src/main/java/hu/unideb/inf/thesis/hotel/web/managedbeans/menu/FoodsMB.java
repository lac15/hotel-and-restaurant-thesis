package hu.unideb.inf.thesis.hotel.web.managedbeans.menu;

import hu.unideb.inf.thesis.hotel.client.api.service.FoodService;
import hu.unideb.inf.thesis.hotel.client.api.vo.FoodVo;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "foodsMB")
public class FoodsMB {

    @EJB
    private FoodService foodService;

    private List<FoodVo> soups = new ArrayList<FoodVo>();
    private List<FoodVo> mainCourses = new ArrayList<FoodVo>();
    private List<FoodVo> desserts = new ArrayList<FoodVo>();
    private String soup;
    private String mainCourse;
    private String dessert;

    @PostConstruct
    public void init() {
        /*soups.addAll();
        mainCourses.addAll();
        desserts.addAll();*/
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

    public String getSoup() {
        return soup;
    }

    public void setSoup(String soup) {
        this.soup = soup;
    }

    public String getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(String mainCourse) {
        this.mainCourse = mainCourse;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }
}
