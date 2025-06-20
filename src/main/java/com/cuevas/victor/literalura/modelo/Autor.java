package com.cuevas.victor.literalura.modelo;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer nacimiento;
    private Integer fallecimiento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Libro> libros;

    // Getters
    public Long getId() { return id; }
    public String getNombre() { return nombre; }
    public Integer getNacimiento() { return nacimiento; }
    public Integer getFallecimiento() { return fallecimiento; }
    public List<Libro> getLibros() { return libros; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setNacimiento(Integer nacimiento) { this.nacimiento = nacimiento; }
    public void setFallecimiento(Integer fallecimiento) { this.fallecimiento = fallecimiento; }
    public void setLibros(List<Libro> libros) { this.libros = libros; }
}
