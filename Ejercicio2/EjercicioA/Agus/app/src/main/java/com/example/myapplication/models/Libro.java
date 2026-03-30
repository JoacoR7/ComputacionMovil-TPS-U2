package com.example.myapplication.models;

public class Libro {
    //Atributos
    private int id;
    private String titulo, subt, isbn, autor;
    private int anioPublicacion;

    private double precio;
    //Constructor
    public Libro(int id, String titulo, String subt, String isbn, String autor, int anioPublicacion, double precio) {
        this.id = id;
        this.titulo = titulo;
        this.subt = subt;
        this.isbn = isbn;
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
        this.precio = precio;
    }

    public Libro() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubt() {
        return subt;
    }

    public void setSubt(String subt) {
        this.subt = subt;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", subt='" + subt + '\'' +
                ", isbn='" + isbn + '\'' +
                ", autor='" + autor + '\'' +
                ", anioPublicacion=" + anioPublicacion +
                ", precio=" + precio +
                '}';
    }
}
