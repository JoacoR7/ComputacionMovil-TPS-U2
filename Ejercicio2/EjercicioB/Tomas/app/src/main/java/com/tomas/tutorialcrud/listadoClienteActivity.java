package com.tomas.tutorialcrud;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tomas.tutorialcrud.adaptador.AdapterClientes;
import com.tomas.tutorialcrud.entidades.Cliente;
import com.tomas.tutorialcrud.entidades.ConexionSQLite;
import com.tomas.tutorialcrud.utilidades.Utilidades;

import java.util.ArrayList;

public class listadoClienteActivity extends AppCompatActivity {

    Button buscar, btnHome;
    EditText txtBuscar;

    RecyclerView recyclerList;
    ConexionSQLite con;
    ArrayList<Cliente> listClientes;
    AdapterClientes adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_listado_cliente);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerList = findViewById(R.id.listaDeCliente);
        buscar = findViewById(R.id.btnBuscar);
        txtBuscar = findViewById(R.id.txtDocBuscar);
        btnHome = findViewById(R.id.btnHome);
        con = new ConexionSQLite(getApplicationContext(), "DB_CLIENTES", null, 1);

        recyclerList.setLayoutManager(new LinearLayoutManager(this));

        listarClientes("");

        buscar.setOnClickListener(v -> listarClientes(txtBuscar.getText().toString()));
        btnHome.setOnClickListener(v -> {
            Intent intent = new Intent(listadoClienteActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }

    private void listarClientes(String documento) {
        SQLiteDatabase db = con.getReadableDatabase();
        Cliente cliente;
        listClientes = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + Utilidades.TABLA_CLIENTE + " WHERE " + Utilidades.CAMPO_DOC + " LIKE '%" + documento + "%'", null);
        while (cursor.moveToNext()) {
            cliente = new Cliente();
            cliente.setIdCliente(cursor.getInt(0));
            cliente.setNombre(cursor.getString(1));
            cliente.setApellido(cursor.getString(2));
            cliente.setDocumento(cursor.getString(3));
            listClientes.add(cliente);
        }
        adapter = new AdapterClientes(listClientes);
        recyclerList.setAdapter(adapter);
        db.close();
    }
}