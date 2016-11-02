package hu.unideb.inf.thesis.hotel.web.managedbeans.register;

import hu.unideb.inf.thesis.hotel.client.api.vo.UserVo;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;

@ManagedBean(name = "registerUser")
@ViewScoped
public class RegisterUserMB implements Serializable {

    private UserVo user = new UserVo();

    public UserVo getUser() {
        return user;
    }

    public void setUser(UserVo user) {
        this.user = user;
    }

}
