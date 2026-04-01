package com.example.video4;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ListView lsvLugares;
    private List<Modelo> modeloListView = new ArrayList<>();
    ListAdapter modelAdapter;
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

        lsvLugares = findViewById(R.id.lsvLugares);
        lsvLugares.setOnItemClickListener(this);

        modeloListView.add(new Modelo("Mendoza", "Mendoza es una ciudad de la región de Cuyo en Argentina y es el corazón de la zona vitivinícola argentina, famosa por sus Malbecs y otros vinos tintos.", R.drawable.mendoza) );
        modeloListView.add(new Modelo("Córdoba", "Córdoba, una provincia de Argentina, se ubica en medio de las praderas de las pampas y las laderas de Sierras de Córdoba.", R.drawable.cordoba) );
        modeloListView.add(new Modelo("Buenos Aires", "La provincia de Buenos Aires es la más grande y poblada de Argentina, con más de 17 millones de habitantes y capital en La Plata.", R.drawable.bsas) );

        modelAdapter = new Adapter(MainActivity.this, R.layout.lugares, modeloListView);
        lsvLugares.setAdapter(modelAdapter);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(this, "Elemento seleccionado: " + (position+1), Toast.LENGTH_LONG ).show();
    }
}