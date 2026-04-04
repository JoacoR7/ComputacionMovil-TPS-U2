package com.example.video1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.squareup.picasso.Picasso;

public class ConsultaActivity extends AppCompatActivity {

    TextView txtNombre, txtInfo;
    ImageView imgPokemon;
    Button btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consulta);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        String nombre = extras.getString("nombre");
        String altura = extras.getString("altura");
        String peso = extras.getString("peso");
        String imagen = extras.getString("imagen");
        txtNombre = findViewById(R.id.txtNombre);
        txtInfo = findViewById(R.id.txtInfo);
        imgPokemon = findViewById(R.id.imgPokemon);
        btnVolver = findViewById(R.id.btnVolver);

        txtNombre.setText(nombre);
        String info = "ID: " + id + "\n" +
                "Altura: " + altura + "\n" +
                "Peso: " + peso;
        txtInfo.setText(info);
        Picasso.get().load(imagen).error(R.mipmap.ic_launcher).into(imgPokemon);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ConsultaActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }
}