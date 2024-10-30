package it.objectmethod.Biblioteca.repository;

import it.objectmethod.Biblioteca.entity.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long>, JpaSpecificationExecutor<Libro> {
}
