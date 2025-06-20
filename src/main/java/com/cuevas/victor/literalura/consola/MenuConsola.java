package com.cuevas.victor.literalura.consola;

import com.cuevas.victor.literalura.servicio.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuConsola {

    @Autowired
    private LibroService servicio;

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMenu() {
        int opcion = -1;

        while (opcion != 0) {
            System.out.println("""
                \n Menú de opciones:
                1 - Buscar libro por título
                2 - Listar todos los libros registrados
                3 - Listar todos los autores registrados
                4 - Listar autores vivos en un año específico
                5 - Listar libros por idioma
                0 - Salir
            """);

            try {
                System.out.print("Elige una opción: ");
                opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1 -> buscarLibro();
                    case 2 -> servicio.listarLibros();
                    case 3 -> servicio.listarAutores();
                    case 4 -> autoresVivos();
                    case 5 -> librosPorIdioma();
                    case 0 -> System.out.println("¡Hasta luego!");
                    default -> System.out.println("⚠Opción no válida. Intenta nuevamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("⚠Debes ingresar un número.");
            }
        }
    }

    private void buscarLibro() {
        System.out.print("Ingresa el título del libro: ");
        String titulo = scanner.nextLine();
        servicio.buscarYGuardarLibro(titulo);
    }

    private void autoresVivos() {
        try {
            System.out.print("Ingresa el año para buscar autores vivos: ");
            int año = Integer.parseInt(scanner.nextLine());
            servicio.autoresVivosEn(año);
        } catch (NumberFormatException e) {
            System.out.println("Año inválido.");
        }
    }

    private void librosPorIdioma() {
        System.out.print("🌍 Ingresa el código de idioma (EN, ES, FR, PT): ");
        String idioma = scanner.nextLine();
        servicio.listarLibrosPorIdioma(idioma);
    }
}
