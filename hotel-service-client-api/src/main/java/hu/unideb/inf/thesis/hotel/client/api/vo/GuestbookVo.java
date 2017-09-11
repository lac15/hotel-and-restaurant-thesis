package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GuestbookVo implements Serializable {

    private static final long serialVersionUID = 6472000328505763772L;

    private Long id;
    private String name;
    private String message;
    private int rating;
    private Date time;

    public GuestbookVo(){
        this.time = new Date();
    }

    public GuestbookVo(Long id, String name, String message, int rating ) {
        this.id = id;
        this.name = name;
        this.message = message;
        this.rating = rating;
        this.time = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
