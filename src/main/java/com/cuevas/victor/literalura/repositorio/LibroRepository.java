package com.cuevas.victor.literalura.repositorio;

import com.cuevas.victor.literalura.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro, Long> {

    // Verifica existencia por título
    boolean existsByTitulo(String titulo);

    // Buscar libros por idioma
    List<Libro> findByIdioma(String idioma);
}
