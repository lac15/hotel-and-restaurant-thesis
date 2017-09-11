package hu.unideb.inf.thesis.hotel.web.managedbeans.guestbook;

import hu.unideb.inf.thesis.hotel.client.api.service.GuestbookService;
import hu.unideb.inf.thesis.hotel.client.api.service.UserService;
import hu.unideb.inf.thesis.hotel.client.api.vo.GuestbookVo;
import hu.unideb.inf.thesis.hotel.client.api.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean(name = "guestbookBean")
@RequestScoped
public class GuestbookMB {

    private static final Logger LOGGER = LoggerFactory.getLogger(GuestbookMB.class);

    @EJB
    private UserService userService;
    @EJB
    private GuestbookService guestbookService;

    private List<GuestbookVo> messages = new ArrayList<>();

    private UserVo user;

    private String message;
    private int rating = 0;

    private GuestbookVo guestbook = new GuestbookVo();

    @PostConstruct
    public void init() {
        messages.addAll(guestbookService.getMessages());
    }

    public void addmsg(){
        String username = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().getName();
        user = userService.getUserByUsername(username);
        guestbook.setName(user.getUsername());
        guestbook.setMessage(message);
        guestbook.setRating(rating);

        guestbookService.addMessage(guestbook);

        rating = 0;
        message = "";

        messages.clear();
        messages.addAll(guestbookService.getMessages());
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<GuestbookVo> getMessages() {
        return messages;
    }

    public void setMessages(List<GuestbookVo> messages) {
        this.messages = messages;
    }

}
