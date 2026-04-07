package com.example.ej2_f;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Application;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class MainActivityViewModel extends AndroidViewModel {
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
    }

    // Funcion para guardar datos primitivos, sin usar objetos
    /*
    public void guardarDatos(String codigo, String descripcion, String precio){

        int codigoInt = Integer.parseInt(codigo);
        double precioDouble = Double.parseDouble(precio);

        File archivo = new File(getApplication().getFilesDir(), "datos.dat");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(archivo, true);

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            DataOutputStream dataOutputStream = new DataOutputStream(bufferedOutputStream);
            dataOutputStream.writeInt(codigoInt);
            dataOutputStream.writeDouble(precioDouble);
            dataOutputStream.writeUTF(descripcion);

            bufferedOutputStream.flush();
            bufferedOutputStream.close();

            Intent intent = new Intent(getApplication(), MostrarActivity.class);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(intent);

        } catch (FileNotFoundException e) {
            Toast.makeText(getApplication(), "File Not Exception", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplication(), "IO Exception", Toast.LENGTH_LONG).show();
        }

    }
    */
    public void guardarDatos(String codigo, String descripcion, String precio){

        int codigoInt = Integer.parseInt(codigo);
        double precioDouble = Double.parseDouble(precio);
        Producto producto = new Producto(codigoInt, precioDouble,descripcion);

        File archivo = new File(getApplication().getFilesDir(), "datos.dat");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(archivo, true);

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);

            ObjectOutputStream objectOutputStream = new ObjectOutputStream(bufferedOutputStream);
            objectOutputStream.writeObject(producto);

            bufferedOutputStream.flush();
            bufferedOutputStream.close();

            Intent intent = new Intent(getApplication(), MostrarActivity.class);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(intent);

        } catch (FileNotFoundException e) {
            Toast.makeText(getApplication(), "File Not Exception", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplication(), "IO Exception", Toast.LENGTH_LONG).show();
        }

    }
}
