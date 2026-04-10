package com.example.envioparametros;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText etNombre;
    Button btnIngresar, btnEnviar;
    TextView tvBienvenidoNombre;

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

        etNombre = findViewById(R.id.etNombre);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnEnviar = findViewById(R.id.btnEnviar);
        tvBienvenidoNombre = findViewById(R.id.tvBienvenidoNombre);

        btnEnviar.setOnClickListener(this::onClick);
        btnIngresar.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        String nombre = etNombre.getText().toString();
        if(nombre.isBlank()) {
            Toast.makeText(MainActivity.this, "Debe ingresar un nombre", Toast.LENGTH_SHORT).show();
            return;
        }
        int id = view.getId();
        if(id == R.id.btnIngresar) {
            tvBienvenidoNombre.setText("Bienvenido: " + nombre);
        } else if (id == R.id.btnEnviar) {
            Intent intent = new Intent(MainActivity.this, MainActivity2.class);
            intent.putExtra("nombre", nombre);
            finish();
            startActivity(intent);
        }
    }
}