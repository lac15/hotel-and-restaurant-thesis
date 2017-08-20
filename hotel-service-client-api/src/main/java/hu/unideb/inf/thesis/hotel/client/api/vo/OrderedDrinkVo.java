package hu.unideb.inf.thesis.hotel.client.api.vo;

import java.io.Serializable;

public class OrderedDrinkVo implements Serializable {

    private static final long serialVersionUID = 2349267328505763772L;

    private Long id;
    private int quantity;

    public OrderedDrinkVo(){}

    public OrderedDrinkVo(Long id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
