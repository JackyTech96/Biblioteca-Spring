package it.objectmethod.Biblioteca.repository;

import it.objectmethod.Biblioteca.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long>, JpaSpecificationExecutor<Persona> {
    //@Query("SELECT p FROM Persona p WHERE p.nome LIKE '[a-z]%'")
    @Query(value = "SELECT * FROM persona p WHERE BINARY LEFT(p.nome, 1) = LOWER(LEFT(p.nome, 1)) AND BINARY LEFT(p.nome, 1) != UPPER(LEFT(p.nome, 1))", nativeQuery = true)
    List<Persona> findAllWithNomeStartingWithLowerCase();

    //    Persona findByEmail(String email);
    Optional<Persona> findByEmail(String email);

    boolean existsByPassword(String password);
}
