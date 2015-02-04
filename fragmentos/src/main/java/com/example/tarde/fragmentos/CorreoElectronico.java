package com.example.tarde.fragmentos;

import java.io.Serializable;

/**
 * Created by tarde on 02/02/2015.
 */
public class CorreoElectronico implements Serializable{

    private String remitente;

    private String asunto;

    private String contenido;

    public CorreoElectronico(String remitente, String asunto, String contenido) {
        this.remitente = remitente;
        this.asunto = asunto;
        this.contenido = contenido;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getRemitente() {

        return remitente;
    }

    public String getAsunto() {
        return asunto;
    }

    public String getContenido() {
        return contenido;
    }

    @Override
    public String toString() {
        return "remitente='"+remitente+'\''+", asunto='"+asunto+'\'';
    }
}
