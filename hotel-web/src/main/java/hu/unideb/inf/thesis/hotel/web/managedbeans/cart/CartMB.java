package hu.unideb.inf.thesis.hotel.web.managedbeans.cart;

import hu.unideb.inf.thesis.hotel.client.api.vo.CartVo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean(name = "cartBean")
@SessionScoped
public class CartMB implements Serializable {

    private CartVo cart = new CartVo();

    public CartVo getCart() {
        return cart;
    }

    public void setCart(CartVo cart) {
        this.cart = cart;
    }
}
