package com.example.myapplication.controllers;

import com.example.myapplication.models.Libro;

import java.util.List;

public interface ILibroBD {
    Libro elemento(int id);
    Libro elemento(String title);

    List<Libro> lista();

    void agregar(Libro libro);

    void borrar(int id);

    void acltualizar(int id, Libro libro);
}
