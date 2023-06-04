package annotations;

import validations.ValidatorCPF;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;


@Documented
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidatorCPF.class)
@Target({
        ElementType.ANNOTATION_TYPE,
        ElementType.TYPE_USE,
        ElementType.PARAMETER,
        ElementType.FIELD
})
public @interface CPF {
    String message() default "CPF invalido!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
