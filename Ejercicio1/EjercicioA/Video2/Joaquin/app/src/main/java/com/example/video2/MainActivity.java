package com.example.video2;

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

public class MainActivity extends AppCompatActivity {

    EditText campoNombre;
    TextView campoBienvenido;
    Button btnIngresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(
                    systemBars.left + 30,
                    systemBars.top + 16,
                    systemBars.right + 30,
                    systemBars.bottom + 16
            );
            return insets;
        });

        campoNombre = findViewById(R.id.txtNombre);
        campoBienvenido = findViewById(R.id.txtBienvenido);
        btnIngresar = findViewById(R.id.btnIngresar);
        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                campoBienvenido.setText("Bienvenido: " + campoNombre.getText());
                Toast.makeText(MainActivity.this, "El nombre es: " + campoNombre.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}