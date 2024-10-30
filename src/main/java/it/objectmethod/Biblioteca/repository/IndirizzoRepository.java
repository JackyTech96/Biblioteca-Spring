package it.objectmethod.Biblioteca.repository;

import it.objectmethod.Biblioteca.entity.Indirizzo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface IndirizzoRepository extends JpaRepository<Indirizzo, Long>, JpaSpecificationExecutor<Indirizzo> {
}
