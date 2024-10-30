package it.objectmethod.Biblioteca.repository;

import it.objectmethod.Biblioteca.entity.Ruolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface RuoloRepository extends JpaRepository<Ruolo, Long>, JpaSpecificationExecutor<Ruolo> {
}
