package it.objectmethod.Biblioteca.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RegexValidator implements ConstraintValidator<RegexValidation, String> {

    private String regexp;

    private String message;

    private boolean nullable;

    @Override
    public void initialize(RegexValidation constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
        this.message = constraintAnnotation.message();
        this.nullable = constraintAnnotation.nullable();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (nullable && value == null) {
            return true; // Se il valore è nullo e sono permessi valori nulli, allora è valido.
        }
        boolean valid = value != null && value.matches(regexp);
        if (!valid) {
//            context.disableDefaultConstraintViolation(); // Disabilita il messaggio di errore predefinito.
            context.buildConstraintViolationWithTemplate(message) // Imposta il messaggio di errore personalizzato.
                    .addConstraintViolation(); // Aggiungi il messaggio al contesto.
        }
        return valid;
    }
}
