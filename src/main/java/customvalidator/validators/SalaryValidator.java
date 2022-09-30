package customvalidator.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class SalaryValidator implements ConstraintValidator<SalaryValidation, Double> {

    @Override
    public boolean isValid(Double salary, ConstraintValidatorContext constraintValidatorContext) {
        if(salary < 0){
            return false;
        }
        return true;
    }
}
