package com.cuevas.victor.literalura.servicio;

import com.cuevas.victor.literalura.dto.AuthorDTO;
import com.cuevas.victor.literalura.dto.BookDTO;
import com.cuevas.victor.literalura.modelo.Autor;
import com.cuevas.victor.literalura.modelo.Libro;
import com.cuevas.victor.literalura.repositorio.AutorRepository;
import com.cuevas.victor.literalura.repositorio.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroService {

    @Autowired
    private GutendexService gutendexService;

    @Autowired
    private LibroRepository libroRepo;

    @Autowired
    private AutorRepository autorRepo;

    public void buscarYGuardarLibro(String titulo) {
        // 1. Validar si ya existe el libro
        if (libroRepo.existsByTitulo(titulo)) {
            System.out.println("El libro ya está registrado en la base de datos.");
            return;
        }

        // 2. Buscar en la API
        Optional<BookDTO> resultado = gutendexService.buscarLibroPorTitulo(titulo);

        if (resultado.isEmpty()) {
            System.out.println("El libro no fue encontrado en la API.");
            return;
        }

        BookDTO dto = resultado.get();

        if (dto.getAuthors().isEmpty()) {
            System.out.println(" El libro no tiene autor, no se puede guardar.");
            return;
        }

        AuthorDTO autorDTO = dto.getAuthors().get(0);

        // 3. Verificar si el autor ya existe
        Autor autor = autorRepo.findByNombre(autorDTO.getName());
        if (autor == null) {
            autor = new Autor();
            autor.setNombre(autorDTO.getName());
            autor.setNacimiento(autorDTO.getBirth_year());
            autor.setFallecimiento(autorDTO.getDeath_year());
            autor = autorRepo.save(autor);
        }

        // 4. Crear y guardar el libro
        Libro libro = new Libro();
        libro.setTitulo(dto.getTitle());
        libro.setIdioma(dto.getLanguages().isEmpty() ? "N/A" : dto.getLanguages().get(0).toUpperCase());
        libro.setNumeroDescargas(dto.getDownload_count());
        libro.setAutor(autor);

        libroRepo.save(libro);

        System.out.println("Libro guardado exitosamente: " + libro.getTitulo());
    }

    public void listarLibros() {
        List<Libro> libros = libroRepo.findAll();
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("Libros registrados:");
            libros.forEach(l -> System.out.printf("- %s (%s) | Autor: %s | Descargas: %d%n",
                    l.getTitulo(), l.getIdioma(), l.getAutor().getNombre(), l.getNumeroDescargas()));
        }
    }

    public void listarAutores() {
        List<Autor> autores = autorRepo.findAll();
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("Autores registrados:");
            autores.forEach(a -> System.out.println("- " + a.getNombre()));
        }
    }

    public void autoresVivosEn(int año) {
        List<Autor> autores = autorRepo.findByNacimientoLessThanEqualAndFallecimientoGreaterThanEqual(año, año);
        if (autores.isEmpty()) {
            System.out.println("Ningún autor estaba vivo en ese año.");
        } else {
            System.out.println("Autores vivos en el año " + año + ":");
            autores.forEach(a -> System.out.println("- " + a.getNombre()));
        }
    }

    public void listarLibrosPorIdioma(String idioma) {
        List<Libro> libros = libroRepo.findByIdioma(idioma.toUpperCase());
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en ese idioma.");
        } else {
            System.out.println("Libros en idioma " + idioma + ":");
            libros.forEach(l -> System.out.println("- " + l.getTitulo()));
        }
    }
}
