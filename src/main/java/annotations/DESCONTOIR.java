package annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import validations.DescontoIRValidator;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = DescontoIRValidator.class)
@Target({
        ElementType.ANNOTATION_TYPE,
        ElementType.TYPE_USE,
        ElementType.PARAMETER,
        ElementType.FIELD
})
public @interface DESCONTOIR {
    String message() default "O valor deve ser 0, 7.5, 15, 22.5 ou 27.5";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
