//package it.objectmethod.Biblioteca.params;
//
//import it.objectmethod.Biblioteca.entity.Personale;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.springframework.data.jpa.domain.Specification;
//
//
//@Data
//@NoArgsConstructor
//public class PersonaleParams {
//    private String nomeRuolo;
//
//    public Specification<Personale> toSpecification() {
//        return Specification.<Personale>where(null)
//                .and(filterFromParams(nomeRuolo));
//    }
//
//    public Specification<Personale> filterFromParams(final String nomeRuolo) {
//        return (root, query, criteriaBuilder) -> {
//            if (nomeRuolo != null) {
//                return criteriaBuilder.equal(root.get("ruolo").get("nomeRuolo"), nomeRuolo);
//            }
//            return criteriaBuilder.conjunction();
//        };
//    }
//}
