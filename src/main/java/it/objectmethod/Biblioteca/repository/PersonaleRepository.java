package it.objectmethod.Biblioteca.repository;

import it.objectmethod.Biblioteca.entity.Personale;
import it.objectmethod.Biblioteca.enums.NomeRuolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaleRepository extends JpaRepository<Personale, Long>, JpaSpecificationExecutor<Personale> {
    @Query(value = "SELECT * FROM Personale WHERE p.ruolo.nome_ruolo = :nomeRuolo", nativeQuery = true)
    List<Personale> findByRuolo_NomeRuolo(@Param("nomeRuolo") NomeRuolo nomeRuolo);
}
