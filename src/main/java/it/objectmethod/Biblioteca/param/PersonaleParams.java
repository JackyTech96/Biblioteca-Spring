package it.objectmethod.Biblioteca.param;

import it.objectmethod.Biblioteca.entity.Personale;
import it.objectmethod.Biblioteca.entity.Personale_;
import it.objectmethod.Biblioteca.entity.Ruolo_;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.Specification;


@Data
@NoArgsConstructor
public class PersonaleParams {
    private String nomeRuolo;

    public Specification<Personale> toSpecification() {
        return Specification.<Personale>where(null)
                .and(filterFromParams(nomeRuolo));
    }

    public Specification<Personale> filterFromParams(final String nomeRuolo) {
        return (root, query, criteriaBuilder) -> {
            if (nomeRuolo != null) {
                return criteriaBuilder.equal(root.get(Personale_.RUOLO).get(Ruolo_.NOME_RUOLO), nomeRuolo);
            }
            return criteriaBuilder.conjunction();
        };
    }
}
