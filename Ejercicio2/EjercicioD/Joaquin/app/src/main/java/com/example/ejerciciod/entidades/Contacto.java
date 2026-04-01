package com.example.ejerciciod.entidades;

public class Contacto {
    private int id;
    private String nombre;
    private String telefono;
    private String correo_electronico;

    public Contacto(int id, String nombre, String telefono, String correo_electronico) {
        this.correo_electronico = correo_electronico;
        this.telefono = telefono;
        this.nombre = nombre;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCorreo_electronico() {
        return correo_electronico;
    }

    public void setCorreo_electronicoo(String correo_electronicoo) {
        this.correo_electronico = correo_electronicoo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
