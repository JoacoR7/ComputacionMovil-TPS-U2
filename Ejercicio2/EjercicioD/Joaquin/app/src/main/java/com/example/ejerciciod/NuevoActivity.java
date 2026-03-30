package com.example.ejerciciod;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ejerciciod.db.DbContactos;

public class NuevoActivity extends AppCompatActivity {

    Button button;
    EditText inputTelefono, inputCorreo, inputNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevo);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = findViewById(R.id.btnGuardarRegistro);
        inputNombre = findViewById(R.id.inputTextNombre);
        inputTelefono = findViewById(R.id.inputTelefono);
        inputCorreo = findViewById(R.id.inputCorreo);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbContactos dbContactos = new DbContactos(NuevoActivity.this);
                long id = dbContactos.insertarContacto(inputNombre.getText().toString(), inputTelefono.getText().toString(), inputCorreo.getText().toString());

                if(id > 0) {
                    Toast.makeText(NuevoActivity.this, "Contacto creado exitosamente", Toast.LENGTH_SHORT).show();
                    limpiar();
                } else {
                    Toast.makeText(NuevoActivity.this, "Error al crear contacto", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.nuevoRegistro) {
            Intent intent = new Intent(NuevoActivity.this, NuevoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.paginaPrincipal) {
            Intent intent = new Intent(NuevoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == android.R.id.home) {
            Intent intent = new Intent(NuevoActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void limpiar() {
        inputNombre.setText("");
        inputTelefono.setText("");
        inputCorreo.setText("");
    }
}