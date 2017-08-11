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

@FacesValidator("endDateValidator")
public class EndDateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Date normalEndDate = (Date) value;
        LocalDateTime endDate = LocalDateTime.ofInstant(normalEndDate.toInstant(), ZoneId.systemDefault());

        UIInput startDateComponent = (UIInput) component.getAttributes().get("startTime");;
        Date normalStartDate = (Date) startDateComponent.getValue();
        LocalDateTime startDate = LocalDateTime.now();
        if (normalStartDate != null) {
            startDate = LocalDateTime.ofInstant(normalStartDate.toInstant(), ZoneId.systemDefault());
        }

        ResourceBundle bundle;
        try {
            bundle = ResourceBundle.getBundle("Messages", context.getViewRoot().getLocale());
        } catch (MissingResourceException e) {
            bundle = ResourceBundle.getBundle("Messages", Locale.ENGLISH);
        }

        if (endDate.isBefore(startDate)) {
            throw new ValidatorException(new FacesMessage(bundle.getString("endDateValidator.isBefore")));
        }

    }
}

