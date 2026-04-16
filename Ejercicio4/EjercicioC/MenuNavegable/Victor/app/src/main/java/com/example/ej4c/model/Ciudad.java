package com.example.ej4c.model;

public class Ciudad {

    private String nombre;
    private String habitantes;
    private String distanciaKm;
    private int imagenResId;     // referencia a la imagen

    public Ciudad(String nombre, String habitantes, String distanciaKm, int imagenResId) {
        this.nombre       = nombre;
        this.habitantes   = habitantes;
        this.distanciaKm  = distanciaKm;
        this.imagenResId  = imagenResId;
    }

    // Getters
    public String getNombre()      { return nombre; }
    public String getHabitantes()     { return habitantes; }
    public String getDistanciaKm() { return distanciaKm; }
    public int getImagenResId()    { return imagenResId; }
}
