package it.objectmethod.Biblioteca.repository;

import it.objectmethod.Biblioteca.entity.PersonaIndirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaIndirizzoRepository extends JpaRepository<PersonaIndirizzo, Long>, JpaSpecificationExecutor<PersonaIndirizzo> {
}
