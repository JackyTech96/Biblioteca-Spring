package it.objectmethod.Biblioteca.repository;

import it.objectmethod.Biblioteca.entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UtenteRepository extends JpaRepository<Utente, Long>, JpaSpecificationExecutor<Utente> {
}
