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

@FacesValidator("startTimeValidator")
public class StartTimeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Date normalStartTime = (Date) value;
        LocalDateTime startTime = LocalDateTime.ofInstant(normalStartTime.toInstant(), ZoneId.systemDefault());

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", context.getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        LocalDateTime checkTime = LocalDateTime.now().plusHours(1).withMinute(0).withSecond(0).withNano(0);
        if (startTime.isBefore(checkTime)) {
            throw new ValidatorException(new FacesMessage(bundle.getString("startDateValidator.isBefore")));
        }

        LocalDateTime checkOpen = startTime.withHour(6);
        if (startTime.isBefore(checkOpen)) {
            throw new ValidatorException(new FacesMessage(bundle.getString("startDateValidator.isBefore")));
        }

        LocalDateTime checkClose = startTime.withHour(21);
        if (startTime.isAfter(checkClose)) {
            throw new ValidatorException(new FacesMessage(bundle.getString("startDateValidator.isBefore")));
        }

        LocalDateTime checkMinutes = startTime.withMinute(0);
        if (startTime.isAfter(checkMinutes)) {
            throw new ValidatorException(new FacesMessage("csak egész órát lehet megadni"));
        }
    }
}
