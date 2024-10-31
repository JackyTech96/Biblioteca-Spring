package it.objectmethod.Biblioteca.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsbnAnnoValidator.class)
public @interface IsbnAnnoValidation {
    String message() default "L'ISBN deve contenere l'anno di pubblicazione";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
