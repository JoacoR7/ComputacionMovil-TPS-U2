package com.tomas.listacustom;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.lista), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final ArrayList<Fruta> frutas = new ArrayList<>();
        frutas.add(new Fruta("PIÑA",
                Color.parseColor("#FFFF00"),
                "Países tropicales como Costa Rica, Filipinas, Brasil y también en zonas cálidas de Centroamérica",
                "Vitamina C, vitamina B1, vitamina B6", R.drawable.anana));

        frutas.add(new Fruta("NARANJA",
                Color.parseColor("#FFA500"),
                "Países con clima cálido o templado como Brasil, Estados Unidos, España, México",
                "Vitamina C, vitamina A, vitamina B9 (ácido fólico)", R.drawable.naranja));

        frutas.add(new Fruta("FRESA",
                Color.RED,
                "Zonas templadas como Estados Unidos, México, España, China",
                "Vitamina C, vitamina B9, vitamina K", R.drawable.frutilla));

        frutas.add(new Fruta("PERA",
                Color.GREEN,
                "Regiones templadas como China, Argentina, Italia, España",
                "Vitamina C, vitamina K, vitaminas del complejo B", R.drawable.pera));

        frutas.add(new Fruta("MANGO",
                Color.parseColor("#FFB347"),
                "Zonas tropicales y subtropicales: India, México, Tailandia, Filipinas, Brasil, Centroamérica",
                "Vitamina A, vitamina C, vitamina E, vitaminas del complejo B", R.drawable.mango));

        final Adaptador adaptador = new Adaptador(this, R.layout.activity_custom, frutas);

        ListView lista = findViewById(R.id.lista);
        lista.setAdapter(adaptador);
    }
}