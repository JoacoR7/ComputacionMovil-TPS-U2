package com.example.ej4_a_video2;

public class Pais {
    private String nombre;
    private String capital;

    private int bandera;
    private String poblacion;
    private String region;

    public Pais(String nombre, String capital, int bandera, String poblacion, String region) {
        this.nombre = nombre;
        this.capital = capital;
        this.bandera = bandera;
        this.poblacion = poblacion;
        this.region = region;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public int getBandera() {
        return bandera;
    }

    public void setBandera(int bandera) {
        this.bandera = bandera;
    }

    public String getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(String poblacion) {
        this.poblacion = poblacion;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
