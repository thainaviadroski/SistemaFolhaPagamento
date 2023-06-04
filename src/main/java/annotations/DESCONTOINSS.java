package annotations;

import jakarta.validation.Constraint;
import validations.DescontoINSSValidator;

import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DescontoINSSValidator.class)
@ReportAsSingleViolation
public @interface DESCONTOINSS {
    String message() default "O valor deve ser 7.5, 9, 12 ou 14";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
