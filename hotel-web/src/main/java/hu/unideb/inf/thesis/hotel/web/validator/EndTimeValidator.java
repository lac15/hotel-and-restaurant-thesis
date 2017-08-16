package hu.unideb.inf.thesis.hotel.web.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
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

@FacesValidator("endTimeValidator")
public class EndTimeValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Date normalEndTime = (Date) value;
        LocalDateTime endTime = LocalDateTime.ofInstant(normalEndTime.toInstant(), ZoneId.systemDefault());

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", context.getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        UIInput startTimeComponent = (UIInput) component.getAttributes().get("startTime");;
        Date normalStartTime = (Date) startTimeComponent.getValue();
        LocalDateTime startTime = LocalDateTime.now();
        if (normalStartTime != null) {
            startTime = LocalDateTime.ofInstant(normalStartTime.toInstant(), ZoneId.systemDefault());
        }
        if (endTime.isBefore(startTime) || endTime.compareTo(startTime) == 0) {
            throw new ValidatorException(new FacesMessage(bundle.getString("endDateValidator.isBefore")));
        }

        LocalDateTime checkOpen = endTime.withHour(7);
        if (endTime.isBefore(checkOpen)) {
            throw new ValidatorException(new FacesMessage(bundle.getString("endDateValidator.isBefore")));
        }

        LocalDateTime checkClose = endTime.withHour(22);
        if (endTime.isAfter(checkClose)) {
            throw new ValidatorException(new FacesMessage(bundle.getString("endDateValidator.isBefore")));
        }

        LocalDateTime checkMinutes = endTime.withMinute(0);
        if (endTime.isAfter(checkMinutes)) {
            throw new ValidatorException(new FacesMessage("csak egész órát lehet megadni"));
        }

    }
}

