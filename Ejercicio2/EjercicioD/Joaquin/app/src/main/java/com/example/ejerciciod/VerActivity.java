package com.example.ejerciciod;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
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
import com.example.ejerciciod.entidades.Contacto;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;

public class VerActivity extends AppCompatActivity {

    EditText inputTextNombre, inputTelefono, inputCorreo;
    Button btn;
    FloatingActionButton fabEditar, fabEliminar;

    Contacto contacto;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        inputTextNombre = findViewById(R.id.inputTextNombre);
        inputTelefono = findViewById(R.id.inputTelefono);
        inputCorreo = findViewById(R.id.inputCorreo);
        btn = findViewById(R.id.btnEdit);
        fabEditar = findViewById(R.id.floatingActionButton);
        fabEliminar = findViewById(R.id.floatingActionButton2);
        fabEliminar.setVisibility(View.INVISIBLE);

        btn.setVisibility(View.INVISIBLE);

        if(savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        DbContactos dbContactos = new DbContactos(VerActivity.this);
        contacto = dbContactos.verContacto(id);

        if (contacto != null) {
            inputTextNombre.setText(contacto.getNombre());
            inputTelefono.setText(contacto.getTelefono());
            inputCorreo.setText(contacto.getCorreo_electronico());
            inputTextNombre.setInputType(InputType.TYPE_NULL);
            inputTelefono.setInputType(InputType.TYPE_NULL);
            inputCorreo.setInputType(InputType.TYPE_NULL);
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VerActivity.this, EditarActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
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
            Intent intent = new Intent(VerActivity.this, NuevoActivity.class);
            startActivity(intent);
            finish();
        } else if (id == R.id.paginaPrincipal) {
            Intent intent = new Intent(VerActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else if (id == android.R.id.home) {
            Intent intent = new Intent(VerActivity.this, CrearBDActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}