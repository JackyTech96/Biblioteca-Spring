package it.objectmethod.Biblioteca.validation;

import it.objectmethod.Biblioteca.dto.LibroDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsbnAnnoValidator implements ConstraintValidator<IsbnAnnoValidation, LibroDto> {

    @Override
    public boolean isValid(LibroDto libroDto, ConstraintValidatorContext context) {
        if (libroDto.getAnnoPubblicazione() == null || libroDto.getIsbn() == null) {
            return true;
        }
        String annoPubblicazione = String.valueOf(libroDto.getAnnoPubblicazione().getValue());
        return libroDto.getIsbn().contains(annoPubblicazione);
    }
}
