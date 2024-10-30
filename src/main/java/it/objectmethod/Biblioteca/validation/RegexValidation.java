package it.objectmethod.Biblioteca.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RegexValidator.class)
public @interface RegexValidation {

    boolean nullable() default false;

    String regexp();

    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
