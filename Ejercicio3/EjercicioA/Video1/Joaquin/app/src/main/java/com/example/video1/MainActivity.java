package com.example.video1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.video1.model.ModeloRetorno;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private Button btn_request;
    private TextInputEditText txt_consulta;
    private String response = "", imagen = "";
    public ModeloRetorno pokedex = new ModeloRetorno();

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

        btn_request = findViewById(R.id.btn_request);
        txt_consulta = findViewById(R.id.txt_consulta);

        btn_request.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View v) {
                ConsultarApi consultarApi = new ConsultarApi();

                try {
                    consultarApi.respuesta(txt_consulta.getText().toString());
                    Toast.makeText(MainActivity.this, "Consultando Pokemon", Toast.LENGTH_SHORT).show();
                    new Handler(getMainLooper()).postDelayed(new Runnable() {
                    @Override
                        public void run() {
                            pokedex = consultarApi.modeloRetorno;
                            response = "ID: " + pokedex.getId() + "\n" +
                                    "Nombre: " + pokedex.getName() + "\n" +
                                    "Altura: " + pokedex.getHeight() + "\n" +
                                    "Peso: " + pokedex.getWeight();
                            imagen = pokedex.getFront_default();

                            if(!response.equals("")) {
                                Intent intent = new Intent(MainActivity.this, ConsultaActivity.class);
                                intent.putExtra("id", pokedex.getId());
                                intent.putExtra("nombre", pokedex.getName());
                                intent.putExtra("altura", pokedex.getHeight());
                                intent.putExtra("peso", pokedex.getWeight());
                                intent.putExtra("imagen", pokedex.getFront_default());
                                startActivity(intent);
                            }
                        }}, 2000);
                } catch (Exception e) {
                    System.out.println(e);
                }
            }});

    }
}