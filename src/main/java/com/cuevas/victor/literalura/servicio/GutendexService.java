package com.cuevas.victor.literalura.servicio;

import com.cuevas.victor.literalura.dto.BookDTO;
import com.cuevas.victor.literalura.dto.GutendexResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
public class GutendexService {

    private final RestTemplate restTemplate = new RestTemplate();

    public Optional<BookDTO> buscarLibroPorTitulo(String titulo) {
        String url = "https://gutendex.com/books/?search=" + titulo;

        try {
            GutendexResponse respuesta = restTemplate.getForObject(url, GutendexResponse.class);

            if (respuesta != null && !respuesta.getResults().isEmpty()) {
                return Optional.of(respuesta.getResults().get(0));
            }
        } catch (Exception e) {
            System.out.println("Error al buscar en la API: " + e.getMessage());
        }

        return Optional.empty();
    }
}
