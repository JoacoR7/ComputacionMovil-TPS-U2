package com.tomas.tutorialcrud;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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

import com.tomas.tutorialcrud.entidades.ConexionSQLite;
import com.tomas.tutorialcrud.utilidades.Utilidades;

public class MainActivity extends AppCompatActivity {

    EditText id, nom, ape, doc;
    Button btnRegistrar;
    Button btnEditar;
    ConexionSQLite conexionSQLite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        id = findViewById(R.id.txtId);
        nom = findViewById(R.id.txtName);
        ape = findViewById(R.id.txtLastName);
        doc = findViewById(R.id.txtDni);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnEditar = findViewById(R.id.btnEditar);

        conexionSQLite = new ConexionSQLite(getApplicationContext(), "DB_CLIENTES", null, 1);
        btnRegistrar.setOnClickListener(v -> registrarCliente());
        btnEditar.setOnClickListener(v -> editarCliente());
    }

    private void registrarCliente() {
        SQLiteDatabase db = conexionSQLite.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_ID, id.getText().toString());
        values.put(Utilidades.CAMPO_NOM, nom.getText().toString());
        values.put(Utilidades.CAMPO_APE, ape.getText().toString());
        values.put(Utilidades.CAMPO_DOC, doc.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_CLIENTE, Utilidades.CAMPO_ID, values);

        Toast.makeText(getApplicationContext(), "Se registro con ID " + idResultante, Toast.LENGTH_LONG).show();
        db.close();
    }

    private void editarCliente() {
        SQLiteDatabase db = conexionSQLite.getWritableDatabase();
        String[] parametro = {id.getText().toString()};
        ContentValues values = new ContentValues();

        values.put(Utilidades.CAMPO_ID, id.getText().toString());
        values.put(Utilidades.CAMPO_NOM, nom.getText().toString());
        values.put(Utilidades.CAMPO_APE, ape.getText().toString());
        values.put(Utilidades.CAMPO_DOC, doc.getText().toString());

        db.update(Utilidades.TABLA_CLIENTE, values, Utilidades.CAMPO_ID+"=?", parametro);
        Toast.makeText(getApplicationContext(), "Se actualizó el cliente", Toast.LENGTH_LONG).show();
        db.close();
    }
}