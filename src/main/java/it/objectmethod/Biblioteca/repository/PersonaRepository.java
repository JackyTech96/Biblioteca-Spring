package it.objectmethod.Biblioteca.repository;

import it.objectmethod.Biblioteca.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>, JpaSpecificationExecutor<Persona> {
    //@Query("SELECT p FROM Persona p WHERE p.nome LIKE '[a-z]%'")
    @Query(value = "SELECT * FROM persona p WHERE BINARY LEFT(p.nome, 1) = LOWER(LEFT(p.nome, 1)) AND BINARY LEFT(p.nome, 1) != UPPER(LEFT(p.nome, 1))", nativeQuery = true)
    List<Persona> findAllWithNomeStartingWithLowerCase();
}
