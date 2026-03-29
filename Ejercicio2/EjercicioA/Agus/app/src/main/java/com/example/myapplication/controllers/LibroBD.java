package com.example.myapplication.controllers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.myapplication.models.Libro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LibroBD extends SQLiteOpenHelper implements ILibroBD{

    Context contexto;
    private List<Libro> librosList = new ArrayList<>();

    public LibroBD(@Nullable Context contexto,
                   @Nullable String name,
                   @Nullable SQLiteDatabase.CursorFactory factory,
                   int version){

        super(contexto, name, factory, version);
        this.contexto = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE libros(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "titulo TEXT, " +
                "subtitulo TEXT, " +
                "isbn TEXT, " +
                "autor TEXT, " +
                "anio INTEGER, " +
                "precio REAL )";
        db.execSQL( sql );

        String insert = "INSERT INTO libros VALUES (null, " +
                "'Como programar', " +
                "'Mas de 80 ej', " +
                "'987654321', " +
                "'Deitel & Deitel', 2008, 145000)";
        db.execSQL( insert );
        insert = "INSERT INTO libros VALUES (null, " +
                "'1984', " +
                "'Distopia en el futuro', " +
                "'123456789', " +
                "'George Orwell', 1949, 50000)";
        db.execSQL( insert);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    @Override
    public Libro elemento(int id) {

        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM libros WHERE _id =" + id;
        Cursor cursor = database.rawQuery(sql, null);

        try{
            if (cursor.moveToNext())
                return extraerLibro(cursor);
            else
                return null;
        } catch (Exception e) {
            Log.d("TAG", "Error elemento(id) LibroBD" + e.getMessage() );
            throw e;
        } finally {
            if (cursor != null) cursor.close();
        }

    }

    private Libro extraerLibro(Cursor cursor) {
        Libro libro = new Libro();
        libro.setId( cursor.getInt(0));
        libro.setTitulo( cursor.getString(1));
        libro.setSubt( cursor.getString(2));
        libro.setIsbn( cursor.getString(3));
        libro.setAutor( cursor.getString(4));
        libro.setAnioPublicacion( cursor.getInt(5));
        libro.setPrecio( cursor.getDouble(6));
        return libro;
    }

    @Override
    public Libro elemento(String title) {
        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM libros WHERE titulo= '" + title + "'";
        Cursor cursor = database.rawQuery(sql, null);

        try{
            if (cursor.moveToNext())
                return extraerLibro(cursor);
            else
                return null;
        } catch (Exception e) {
            Log.d("TAG", "Error elemento(titulo) LibroBD" + e.getMessage() );
            throw e;
        } finally {
            if (cursor != null) cursor.close();
        }
    }

    @Override
    public List<Libro> lista() {

        SQLiteDatabase database = getReadableDatabase();
        String sql = "SELECT * FROM libros ORDER BY titulo ASC";
        Cursor cursor = database.rawQuery(sql, null);
        List<Libro> librosList = new ArrayList<>();

        if (cursor.moveToFirst())
            do{
                librosList.add(
                        new Libro( cursor.getInt(0),
                                cursor.getString(1),
                                cursor.getString(2),
                                cursor.getString(3),
                                cursor.getString(4),
                                cursor.getInt(5),
                                cursor.getDouble(6) )
                );
            }while (cursor.moveToNext());
        cursor.close();
        return librosList;
    }

    @Override
    public void agregar(Libro libro) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("titulo", libro.getTitulo());
        values.put("subtitulo", libro.getSubt());
        values.put("autor", libro.getAutor());
        values.put("isbn", libro.getIsbn());
        values.put("anio", libro.getAnioPublicacion());
        values.put("precio", libro.getPrecio());

        database.insert("libros", null, values);
    }

    @Override
    public void borrar(int id) {
        SQLiteDatabase database = getWritableDatabase();
        String[] parametros = { String.valueOf(id) };

        database.delete("libros", "_id=?", parametros);
    }

    @Override
    public void acltualizar(int id, Libro libro) {
        SQLiteDatabase database = getWritableDatabase();

        String[] parametros = { String.valueOf(id) };

        ContentValues values = new ContentValues();
        values.put("titulo", libro.getTitulo());
        values.put("subtitulo", libro.getSubt());
        values.put("autor", libro.getAutor());
        values.put("isbn", libro.getIsbn());
        values.put("anio", libro.getAnioPublicacion());
        values.put("precio", libro.getPrecio());

        database.update("libros", values, "_id=?", parametros);
    }
}
