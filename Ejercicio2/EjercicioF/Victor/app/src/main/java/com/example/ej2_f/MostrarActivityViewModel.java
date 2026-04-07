package com.example.ej2_f;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MostrarActivityViewModel extends AndroidViewModel {
    private MutableLiveData<String> mProducto = new MutableLiveData<>();

    public MostrarActivityViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getProducto(){
        return mProducto;
    }

    // Leer los datos primitivos sin usar objetos
    /*
    public void leerDatos(){

        StringBuffer stringBuffer = new StringBuffer();
        File file = new File(getApplication().getFilesDir(), "datos.dat");

        // Construir el flujo de entrada de datos
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            DataInputStream dataInputStream = new DataInputStream(bufferedInputStream);
            int codigo = dataInputStream.readInt();
            double precio = dataInputStream.readDouble();
            String descripcion = dataInputStream.readUTF();
            stringBuffer.append(codigo + " " + descripcion + " " + precio + "\n");
            bufferedInputStream.close();
            mProducto.setValue(stringBuffer.toString());

        }catch (FileNotFoundException fileNotFoundException){
            Toast.makeText(getApplication(), "File Not Found Exception", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplication(), "IO Exception", Toast.LENGTH_LONG).show();
        }


    }
    */

    public void leerDatos(){

        StringBuffer stringBuffer = new StringBuffer();
        File file = new File(getApplication().getFilesDir(), "datos.dat");

        // Construir el flujo de entrada de datos
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);

            ObjectInputStream objectInputStream = new ObjectInputStream(bufferedInputStream);
            Producto producto = (Producto) objectInputStream.readObject();


            stringBuffer.append(producto.getCodigo())
                    .append(" ")
                    .append(producto.getDescripcion())
                    .append(" ")
                    .append(producto.getPrecio())
                    .append("\n");

            bufferedInputStream.close();
            mProducto.setValue(stringBuffer.toString());

        }catch (FileNotFoundException fileNotFoundException){
            Toast.makeText(getApplication(), "File Not Found Exception", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(getApplication(), "IO Exception", Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(getApplication(), "Class Not Found Exception", Toast.LENGTH_LONG).show();
        }


    }

}
