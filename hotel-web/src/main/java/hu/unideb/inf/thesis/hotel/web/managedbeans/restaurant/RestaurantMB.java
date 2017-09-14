package hu.unideb.inf.thesis.hotel.web.managedbeans.restaurant;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "restaurantBean")
@RequestScoped
public class RestaurantMB {

    private List<String> images;

    @PostConstruct
    public void init() {
        images = new ArrayList<String>();

        for(int i = 6; i <= 10; i++){
            images.add("gallery_image_" + i + ".jpg");
        }
    }

    public List<String> getImages() {
        return images;
    }
}
