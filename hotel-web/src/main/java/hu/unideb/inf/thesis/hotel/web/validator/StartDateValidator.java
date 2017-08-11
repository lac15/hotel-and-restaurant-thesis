package hu.unideb.inf.thesis.hotel.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@FacesValidator("startDateValidator")
public class StartDateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Date normalStartDate = (Date) value;
        LocalDateTime startDate = LocalDateTime.ofInstant(normalStartDate.toInstant(), ZoneId.systemDefault());

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", context.getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        if (startDate.isBefore(LocalDateTime.now())) {
            throw new ValidatorException(new FacesMessage(bundle.getString("startDateValidator.isBefore")));
        }

    }
}

