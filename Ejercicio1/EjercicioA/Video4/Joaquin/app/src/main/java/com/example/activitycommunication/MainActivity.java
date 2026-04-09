package com.example.activitycommunication;

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

public class MainActivity extends AppCompatActivity {

    EditText etNombre, etApellido, etCelular;
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

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etCelular = findViewById(R.id.etCelular);
        btnEnviar = findViewById(R.id.btnEnviar);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean correctValues = true;
                if(etNombre.getText().toString().isBlank()) {
                    Toast.makeText(MainActivity.this, "Debe colocar el nombre", Toast.LENGTH_SHORT).show();
                    correctValues = false;
                }
                if(etApellido.getText().toString().isBlank()) {
                    Toast.makeText(MainActivity.this, "Debe colocar el nombre", Toast.LENGTH_SHORT).show();
                    correctValues = false;
                }
                if(etCelular.getText().toString().isBlank()) {
                    Toast.makeText(MainActivity.this, "Debe colocar el nombre", Toast.LENGTH_SHORT).show();
                    correctValues = false;
                }
                if(!correctValues) {
                    return;
                }

                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                intent.putExtra("nombre", etNombre.getText().toString());
                intent.putExtra("apellido", etApellido.getText().toString());
                intent.putExtra("celular", etCelular.getText().toString());
                startActivity(intent);
            }
        });
    }
}