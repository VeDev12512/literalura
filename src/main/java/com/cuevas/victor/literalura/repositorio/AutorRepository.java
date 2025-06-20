package com.cuevas.victor.literalura.repositorio;

import com.cuevas.victor.literalura.modelo.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Buscar autores vivos en un año determinado
    List<Autor> findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(int nacimiento, int fallecimiento);

    // Evitar duplicados
    boolean existsByNombre(String nombre);

    // Buscar un autor
    Autor findByNombre(String nombre);
}
