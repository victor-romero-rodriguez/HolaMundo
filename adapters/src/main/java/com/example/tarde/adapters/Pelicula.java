package com.example.tarde.adapters;

import java.io.Serializable;

/**
 * Created by tarde on 03/02/2015.
 */
public class Pelicula implements Serializable {

    private String titulo;
    private int anio;
    private String[] actores;

    public Pelicula() {
    }

    public Pelicula(String titulo, int anio, String[] actores) {
        this.titulo = titulo;
        this.anio = anio;
        this.actores = actores;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String[] getActores() {
        return actores;
    }

    public void setActores(String[] actores) {
        this.actores = actores;
    }
}
