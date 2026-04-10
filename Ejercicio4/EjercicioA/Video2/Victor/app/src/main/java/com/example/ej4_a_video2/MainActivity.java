package com.example.ej4_a_video2;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Pais> listaPaises;
    Adaptador adaptador;

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

        listaPaises = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);

        adaptador = new Adaptador(listaPaises);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adaptador);

        for (int i = 0; i < 20; i++){
            listaPaises.add(new Pais("Argentina", "Buenos Aires", R.drawable.argentina, "45.7", "América del Sur"));
            listaPaises.add(new Pais("Brasil", "Brasilia", R.drawable.brasil, "212", "América del Sur"));
            listaPaises.add(new Pais("Perú", "Lima", R.drawable.peru, "34.2", "América del Sur"));
            listaPaises.add(new Pais("España", "Madrid", R.drawable.espana, "47.3", "Europa"));
            listaPaises.add(new Pais("Chile", "Santiago de Chile", R.drawable.chile, "19.7", "América del Sur"));
            listaPaises.add(new Pais("México", "Ciudad de México", R.drawable.mexico, "131", "América del Norte"));
            listaPaises.add(new Pais("Uruguay", "Montevideo", R.drawable.uruguay, "3.4", "América del Sur"));
        }

    }
}