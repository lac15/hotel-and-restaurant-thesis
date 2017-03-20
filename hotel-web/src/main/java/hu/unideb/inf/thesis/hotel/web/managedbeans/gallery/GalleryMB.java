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

    @PostConstruct
    public void init() {
        images = new ArrayList<String>();

        images.add("bg00.jpg");
        images.add("bg01.jpg");
        images.add("bg02.jpg");
        images.add("img01.jpg");
        images.add("img02.jpg");
    }

    public List<String> getImages() {
        return images;
    }
}
