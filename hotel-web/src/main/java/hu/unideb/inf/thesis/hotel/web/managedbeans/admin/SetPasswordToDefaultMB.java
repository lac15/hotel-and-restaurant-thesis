package hu.unideb.inf.thesis.hotel.web.managedbeans.admin;

import hu.unideb.inf.thesis.hotel.client.api.exception.EmailSendingException;
import hu.unideb.inf.thesis.hotel.client.api.service.MailService;
import hu.unideb.inf.thesis.hotel.client.api.service.UserService;
import hu.unideb.inf.thesis.hotel.client.api.vo.UserVo;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import java.util.*;

@ManagedBean(name = "setPasswordToDefault")
@RequestScoped
public class SetPasswordToDefaultMB {

    @EJB
    private UserService userService;

    @EJB
    private MailService mailService;

    public void setDefault(List<UserVo> users) {
        for (UserVo userVo : users) {
            ResourceBundle bundle;
            try {
                bundle = ResourceBundle.getBundle("Messages", FacesContext.getCurrentInstance().getViewRoot().getLocale());
            } catch (MissingResourceException e) {
                bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
            }

            String newPassword = UUID.randomUUID().toString();
            newPassword = newPassword.substring(0, 8);
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String encPassword = bCryptPasswordEncoder.encode(newPassword);
            userVo.setPassword(encPassword);
            userService.saveUser(userVo);
            try {
                mailService.sendMail("fourseasons.hotelandrestaurant@gmail.com", userVo.getEmail(), "Your new password is: ", newPassword);
            } catch (EmailSendingException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        bundle.getString("admin.resetPassword.error.summary"),
                        bundle.getString("admin.resetPassword.error.detail")));
            }
        }
    }
}
