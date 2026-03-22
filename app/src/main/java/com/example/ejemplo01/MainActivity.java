package com.example.ejemplo01;

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

public class MainActivity extends AppCompatActivity {

    // Campos de texto
    EditText nombre, apellido;
    // Botón enviar
    Button btnEnviar;
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

        // Inicialización de componentes
        btnEnviar = findViewById(R.id.btnEnviar);
        nombre = findViewById(R.id.nombre);
        apellido = findViewById(R.id.apellido);
        
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.getText().toString().isEmpty()) {
                    nombre.setError("Falta colocar el nombre");
                } else if (apellido.getText().toString().isEmpty()) {
                    apellido.setError("Falta colocar el apellido");
                } else {
                    Toast.makeText(getBaseContext(), "Almacenado", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}