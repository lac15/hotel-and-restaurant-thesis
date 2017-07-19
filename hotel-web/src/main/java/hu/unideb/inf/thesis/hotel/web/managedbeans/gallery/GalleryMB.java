package hu.unideb.inf.thesis.hotel.web.managedbeans.gallery;

import org.springframework.web.context.annotation.RequestScope;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "galleryBean")
@RequestScoped
public class GalleryMB {

    private List<String> images;

    private String selectedImage;

    @PostConstruct
    public void init() {
        images = new ArrayList<String>();

        for(int i = 1; i <= 10; i++){
            images.add("gallery_image_" + i + ".jpg");
        }
    }

    public List<String> getImages() {
        return images;
    }

    public String getSelectedImage() {
        return selectedImage;
    }

    public void setSelectedImage(String selectedImage) {
        this.selectedImage = selectedImage;
    }
}
