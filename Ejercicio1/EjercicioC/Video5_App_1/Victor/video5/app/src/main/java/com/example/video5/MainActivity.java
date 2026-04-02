package com.example.video5;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
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

    private EditText etNuevaNota;
    private Button btnGuardar;
    private TextView txvNotas;
    private static final String PREFERENCIAS_NOTAS = "Mis notas";

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

        etNuevaNota = findViewById(R.id.etNuevaNota);
        btnGuardar = findViewById(R.id.btnGuardar);
        txvNotas = findViewById(R.id.txvNotas);
        cargarNotas();

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarNota();
            }
        });

        // Manejando evento del teclado
        etNuevaNota.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                // Verifico si se presiono el enter
                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    guardarNota();
                    return true;
                }

                return false;
            }
        });


    }

    private void guardarNota(){

        String nota = etNuevaNota.getText().toString().trim();

        if (!nota.isEmpty()){
            SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCIAS_NOTAS, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putString("nota", nota);
            editor.apply();

            txvNotas.setText("Nota guardada: "+ nota);
            Toast.makeText(this, "Nota guardada: "+ nota , Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this, "La nota está vacía" , Toast.LENGTH_LONG).show();
        }
    }
    private void cargarNotas(){
        SharedPreferences sharedPreferences = getSharedPreferences(PREFERENCIAS_NOTAS, Context.MODE_PRIVATE);
        String notasGuardadas = sharedPreferences.getString("nota","");
        etNuevaNota.setText(notasGuardadas);
        txvNotas.setText("Notas guardadas: "+ notasGuardadas);

    }


}