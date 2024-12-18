package it.objectmethod.Biblioteca.repository;

import it.objectmethod.Biblioteca.entity.Personale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaleRepository extends JpaRepository<Personale, Long>, JpaSpecificationExecutor<Personale> {
    //    @Query(value = "SELECT * FROM Personale WHERE p.ruolo.nome_ruolo = :nomeRuolo", nativeQuery = true)
//    @Query(value = "SELECT * FROM personale p JOIN ruolo r ON p.ruolo_id = r.ruolo_id WHERE r.nome_ruolo = :nomeRuolo", nativeQuery = true)
    @Query("SELECT p FROM Personale p JOIN p.ruolo r WHERE LOWER(r.nomeRuolo) = LOWER(:nomeRuolo)")
    List<Personale> findByRuolo_NomeRuolo(@Param("nomeRuolo") String nomeRuolo);
}
