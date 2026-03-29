package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.controllers.LibroBD;
import com.example.myapplication.models.Libro;

public class GestionarLibroActivity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    EditText txttitulo, txtsubtitulo, txtisbn, txtautor, txtaniopublicacion, txtprecio;
    int id;
    LibroBD libroBD;

    Button btnguardar, btnactualizar, btnborrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gestionar_libro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        init();
    }
    
    private void init(){
        context = getApplicationContext();
        txttitulo = findViewById(R.id.ges_txttitulo);
        txtsubtitulo = findViewById(R.id.ges_txtsubtitulo);
        txtisbn = findViewById(R.id.ges_txtisbn);
        txtautor = findViewById(R.id.ges_txtautor);
        txtaniopublicacion = findViewById(R.id.ges_txtaniopublicacion);
        txtprecio = findViewById(R.id.ges_txtprecio);

        btnguardar = findViewById(R.id.ges_btnguardar);
        btnactualizar = findViewById(R.id.ges_btnactualizar);
        btnborrar = findViewById(R.id.ges_btnborrar);

        Intent i = getIntent();
        Bundle bolsa = i.getExtras();
        id = bolsa.getInt("id");

        if (id != 0) {
            llenarCampos();
        } else {
            limpiarCampos();
        }

    }

    private void llenarCampos(){

        Intent i = getIntent();
        Bundle bolsa = i.getExtras();

        id = bolsa.getInt("id");
        if (id != 0) {
            txttitulo.setText(bolsa.getString("titulo"));
            txtsubtitulo.setText(bolsa.getString("subtitulo"));
            txtisbn.setText(bolsa.getString("isbn"));
            txtautor.setText(bolsa.getString("autor"));
            txtaniopublicacion.setText(String.valueOf(bolsa.getInt("anio_publicacion")));
            txtprecio.setText(String.valueOf(bolsa.getDouble("precio")));
            btnguardar.setEnabled(false);
        } else {
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
        }
    }

    private void limpiarCampos(){
        id = 0;
        txttitulo.setText("");
        txtsubtitulo.setText("");
        txtisbn.setText("");
        txtautor.setText("");
        txtaniopublicacion.setText("");
        txtprecio.setText("");
    }

    private Libro llenarDatosLibro(){
        Libro libro = new Libro();

        libro.setId(id);
        String t = txttitulo.getText().toString();
        String s = txtsubtitulo.getText().toString();
        String i = txtisbn.getText().toString();
        String a = txtautor.getText().toString();
        int ap = Integer.parseInt(txtaniopublicacion.getText().toString());
        double p = Double.parseDouble(txtprecio.getText().toString());

        libro.setTitulo(t);
        libro.setSubt(s);
        libro.setIsbn(i);
        libro.setAutor(a);
        libro.setAnioPublicacion(ap);
        libro.setPrecio(p);

        return libro;
    }

    private void guardar(){
        libroBD = new LibroBD(context, "librosBD.db", null, 1);
        Libro libro = llenarDatosLibro();
        if (id == 0) {
            libroBD.agregar(libro);
            Toast.makeText(context, "Guardado Nuevo OK", Toast.LENGTH_LONG).show();
            finish();
        } else {
            libroBD.acltualizar(id, libro);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            Toast.makeText(context, "Actualizado OK", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    private void borrar(){
        libroBD = new LibroBD(context, "librosBD.db", null, 1);
        Libro libro = llenarDatosLibro();
        if (id == 0) {
            Toast.makeText(context, "No es posible eliminar el libro", Toast.LENGTH_LONG).show();
        } else {
            libroBD.borrar(id);
            limpiarCampos();
            btnguardar.setEnabled(true);
            btnactualizar.setEnabled(false);
            btnborrar.setEnabled(false);
            Toast.makeText(context, "Borrado OK", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == R.id.ges_btnguardar) {
            guardar();
        } else if (id == R.id.ges_btnactualizar) {
            guardar();
        } else if (id == R.id.ges_btnborrar) {
            borrar();
        }
    }
}