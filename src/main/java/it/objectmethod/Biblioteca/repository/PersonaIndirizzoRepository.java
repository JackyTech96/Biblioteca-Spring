package it.objectmethod.Biblioteca.repository;


import it.objectmethod.Biblioteca.entity.PersonaIndirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaIndirizzoRepository extends JpaRepository<PersonaIndirizzo, Long>, JpaSpecificationExecutor<PersonaIndirizzo> {
    @Query("SELECT pi FROM PersonaIndirizzo pi JOIN pi.persona p JOIN pi.indirizzo i WHERE p.personaId = :personaId")
    List<PersonaIndirizzo> findAllPersonaWithIndirizzo(@Param("personaId") Long personaId);
}
