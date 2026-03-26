package com.tomas.tutorialcrud.entidades;

public class Cliente {
    int idCliente;
    String nombre, apellido, documento;

    public Cliente() {
    }

    public Cliente(int idCliente, String apellido, String nombre, String documento) {
        this.idCliente = idCliente;
        this.apellido = apellido;
        this.nombre = nombre;
        this.documento = documento;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
}

