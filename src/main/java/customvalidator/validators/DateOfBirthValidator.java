package customvalidator.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;
import java.time.Instant;
import static java.util.Date.from;

public class DateOfBirthValidator implements ConstraintValidator<DateOfBirthValidation, Date>
{
    @Override
    public boolean isValid(Date date, ConstraintValidatorContext constraintValidatorContext) {
        Instant instant = Instant.now().minusSeconds(18 * 365 * 24 * 60 * 60);
        java.util.Date dateNow = from(instant);
        if(date.before(dateNow)){
            return true;
        }
        return false;
    }
}