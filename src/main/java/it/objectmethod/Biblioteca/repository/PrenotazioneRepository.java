package it.objectmethod.Biblioteca.repository;

import it.objectmethod.Biblioteca.entity.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PrenotazioneRepository extends JpaRepository<Prenotazione, Long>, JpaSpecificationExecutor<Prenotazione> {
}
