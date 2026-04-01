package com.example.ejerciciod.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.ejerciciod.entidades.Contacto;

import java.util.ArrayList;

public class DbContactos extends DbHelper{

    Context context;
    public DbContactos(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarContacto(String nombre, String telefono, String correo_electronico) {
        long id = 0;
        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("telefono", telefono);
            values.put("correo_electronico", correo_electronico);
            id = db.insert(TABLE_CONTACTOS, null, values);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return id;
    }

    public ArrayList<Contacto> mostrarContactos() {
        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contacto> listaContactos = new ArrayList<>();

        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("SELECT * FROM " + TABLE_CONTACTOS, null);

        if(cursorContactos.moveToFirst()) {
            do {
                Contacto contacto = new Contacto(cursorContactos.getInt(0), cursorContactos.getString(1),
                        cursorContactos.getString(2), cursorContactos.getString(3));
                listaContactos.add(contacto);
            } while (cursorContactos.moveToNext());
            cursorContactos.close();;
        }
        return listaContactos;
    }
}
