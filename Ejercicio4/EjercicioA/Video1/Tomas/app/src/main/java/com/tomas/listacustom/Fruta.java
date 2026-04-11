package com.tomas.listacustom;

import java.io.Serializable;

public class Fruta implements Serializable {

    private String nombre;
    private int color;
    private String lugar;
    private String vitaminas;
    private int foto;

    public Fruta(String nombre, int color, String lugar, String vitaminas, int foto) {
        this.nombre = nombre;
        this.color = color;
        this.lugar = lugar;
        this.vitaminas = vitaminas;
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getVitaminas() {
        return vitaminas;
    }

    public void setVitaminas(String vitaminas) {
        this.vitaminas = vitaminas;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }
}
