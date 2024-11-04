package it.objectmethod.Biblioteca.validation;

import it.objectmethod.Biblioteca.dto.LibroDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsbnAnnoValidator implements ConstraintValidator<IsbnAnnoValidation, LibroDto> {
    private String message;

    @Override
    public void initialize(IsbnAnnoValidation constraintAnnotation) {
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(final LibroDto libroDto, final ConstraintValidatorContext context) {
        // Se l'ISBN o l'anno di pubblicazione sono null, il valore è valido
        if (libroDto.getAnnoPubblicazione() == null || libroDto.getIsbn() == null) {
            return true;
        }
        //Controlla se l'ISBN contiene l'anno di pubblicazione
        String annoPubblicazione = String.valueOf(libroDto.getAnnoPubblicazione().getValue());
        boolean valid = libroDto.getIsbn().contains(annoPubblicazione);

        if (!valid) {
//            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message)
                    // Specifica "isbn" come proprietà dell'errore per visualizzarlo correttamente nei dettagli di validazione
                    .addPropertyNode("isbn")
                    .addConstraintViolation();
        }
        return valid;
    }
}
