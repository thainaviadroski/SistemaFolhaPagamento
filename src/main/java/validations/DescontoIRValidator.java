package validations;

import annotations.DESCONTOIR;
import jakarta.validation.ConstraintValidator;

public class DescontoIRValidator implements ConstraintValidator<DESCONTOIR, Double> {
    @Override
    public void initialize(DESCONTOIR constraintAnnotation) {
    }

    @Override
    public boolean isValid(Double aDouble, jakarta.validation.ConstraintValidatorContext constraintValidatorContext) {
        //return value == 0  value == 7.5  value == 15  value == 22.5  value == 27.5;
        if (aDouble == 0) {
            return false;
        }

        if (aDouble == 7.5) {
            return true;
        }

        if (aDouble == 15) {
            return true;
        }

        if (aDouble == 22.5) {
            return true;
        }

        if (aDouble == 27.5) {
            return true;
        }

        return false;
    }


}