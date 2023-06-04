package validations;

import annotations.DESCONTOINSS;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DescontoINSSValidator implements ConstraintValidator<DESCONTOINSS, Double> {

    @Override
    public void initialize(DESCONTOINSS constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double value, ConstraintValidatorContext constraintValidatorContext) {
        return value == 7.5 || value == 9 || value == 12 || value == 14;
    }
}
