package com.cuevas.victor.literalura.modelo;

import jakarta.persistence.*;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private Integer numeroDescargas;

    @ManyToOne
    private Autor autor;

    // Getters
    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getIdioma() { return idioma; }
    public Integer getNumeroDescargas() { return numeroDescargas; }
    public Autor getAutor() { return autor; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setIdioma(String idioma) { this.idioma = idioma; }
    public void setNumeroDescargas(Integer numeroDescargas) { this.numeroDescargas = numeroDescargas; }
    public void setAutor(Autor autor) { this.autor = autor; }
}
