package hu.unideb.inf.thesis.hotel.web.managedbeans.contact;

import hu.unideb.inf.thesis.hotel.client.api.service.UserService;
import hu.unideb.inf.thesis.hotel.client.api.vo.UserVo;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name="contactBean")
@RequestScoped
public class ContactMB {
    @EJB
    private UserService userService;

    private UserVo user;

    private MapModel geoModel;
    private String centerGeoMap;

    @PostConstruct
    public void init() {
        geoModel = new DefaultMapModel();
        user = userService.getUserByName("owner");
        double locLat = 47.541637;
        double locLng = 21.638548;
        centerGeoMap = locLat + "," + locLng;
        LatLng loc = new LatLng(locLat, locLng);
        geoModel.addOverlay(new Marker(loc, user.getAddress()));
    }

    public MapModel getGeoModel() {
        return geoModel;
    }

    public String getCenterGeoMap() {
        return centerGeoMap;
    }

    public UserVo getUser() {
        return user;
    }
}
