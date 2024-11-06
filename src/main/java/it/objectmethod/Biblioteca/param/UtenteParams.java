package it.objectmethod.Biblioteca.param;

import it.objectmethod.Biblioteca.entity.Persona;
import it.objectmethod.Biblioteca.entity.Persona_;
import it.objectmethod.Biblioteca.entity.Utente;
import it.objectmethod.Biblioteca.entity.Utente_;
import jakarta.persistence.criteria.Join;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

@Data
@NoArgsConstructor
public class UtenteParams {
    private Long utenteId;
    private String nome;
    private String email;


    public Specification<Utente> toSpecification() {
        return Specification.<Utente>where(null)
                .and(equalIdSpecification(utenteId))
                .and(equalNomeSpecification(nome))
                .and(equalEmailSpecification(email));
    }

    public Specification<Utente> equalIdSpecification(final Long id) {
        if (id == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(Utente_.UTENTE_ID), id);
    }

    public Specification<Utente> equalNomeSpecification(final String nome) {
        if (nome == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
//            Join<Utente, Persona> persona = root.join(Utente_.PERSONA);
            return criteriaBuilder.equal(root.get(Utente_.PERSONA).get(Persona_.NOME), nome);
        };
    }

    public Specification<Utente> equalEmailSpecification(final String email) {
        if (email == null) {
            return null;
        }
        return (root, query, criteriaBuilder) ->
        {
            Join<Utente, Persona> persona = root.join(Utente_.PERSONA);
            return criteriaBuilder.equal(persona.get(Persona_.EMAIL), email);
        };
    }
}




