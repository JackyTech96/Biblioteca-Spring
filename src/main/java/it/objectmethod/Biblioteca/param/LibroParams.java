package it.objectmethod.Biblioteca.param;

import io.micrometer.common.lang.Nullable;
import it.objectmethod.Biblioteca.entity.Libro;
import it.objectmethod.Biblioteca.entity.Libro_;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.Year;

@Data
@NoArgsConstructor
public class LibroParams {

    private Long libroId;
    @Nullable
    private String titolo;
    @Nullable
    private String autore;
    @Nullable
    private String isbn;
    @Nullable
    private String genere;
    @Nullable
    private String editore;
    @Nullable
    private Year annoPubblicazione;

    public Specification<Libro> toSpecification() {
        return Specification.<Libro>where(null) // where 0 = 0 and libro_id = ?
                .and(equalIdSpecification(libroId))
                .and(equalTitoloSpecification(titolo))
                .and(equalAutoreSpecification(autore))
                .and(equalIsbnSpecification(isbn))
                .and(equalGenereSpecification(genere))
                .and(equalEditoreSpecification(editore))
                .and(equalAnnoPubblicazioneSpecification(annoPubblicazione));
    }

    private Specification<Libro> equalIdSpecification(final Long libroId) {
        if (libroId == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Libro_.LIBRO_ID), libroId);
    }

    private Specification<Libro> equalTitoloSpecification(final String titolo) {
        if (titolo == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Libro_.TITOLO), titolo);
    }

    private Specification<Libro> equalAutoreSpecification(final String autore) {
        if (autore == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Libro_.AUTORE), autore);
    }

    private Specification<Libro> equalIsbnSpecification(final String isbn) {
        if (isbn == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Libro_.ISBN), isbn);
    }

    private Specification<Libro> equalGenereSpecification(final String genere) {
        if (genere == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Libro_.GENERE), genere);
    }

    private Specification<Libro> equalEditoreSpecification(final String editore) {
        if (editore == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Libro_.EDITORE), editore);
    }

    private Specification<Libro> equalAnnoPubblicazioneSpecification(final Year annoPubblicazione) {
        if (annoPubblicazione == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Libro_.ANNO_PUBBLICAZIONE), annoPubblicazione);
    }
}
