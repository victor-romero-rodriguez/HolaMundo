package com.example.tarde.contentresolver_clientecontentprovider;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by tarde on 09/02/2015.
 */
public class Noticia implements Serializable {

    public static final String TABLA = "NOTICIAS";

    private String titulo;
    private String link;
    private String descripcion;
    private String creador;
    private String id;
    private Date fechaPublicacion;
    private String contenido;

    public Noticia() {
    }

    public Noticia(String id, String titulo, String link, String creador, String descripcion, Date fechaPublicacion, String contenido) {
        this.titulo = titulo;
        this.link = link;
        this.descripcion = descripcion;
        this.creador = creador;
        this.id = id;
        this.fechaPublicacion = fechaPublicacion;
        this.contenido = contenido;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {

        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCreador() {
        return creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    public Date getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    @Override
    public String toString() {
        return "Titulo " + this.titulo + ",Autor " + this.creador;
    }
}
