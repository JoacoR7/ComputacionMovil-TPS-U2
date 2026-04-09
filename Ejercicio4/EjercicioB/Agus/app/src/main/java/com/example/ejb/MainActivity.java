package com.example.ejb;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnRed = findViewById(R.id.btnRed);
        Button btnGreen = findViewById(R.id.btnGreen);
        Button btnBlue = findViewById(R.id.btnBlue);

        btnRed.setOnClickListener(v -> cambiarFragment(new FragmentRojo()));
        btnGreen.setOnClickListener(v -> cambiarFragment(new FragmentVerde()));
        btnBlue.setOnClickListener(v -> cambiarFragment(new FragmentAzul()));
    }

    private void cambiarFragment(Fragment fragmentoNuevo) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragments, fragmentoNuevo) // 'replace' quita el anterior y pone el nuevo
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE) // Añade una transición suave
                .commit(); // Ejecuta el cambio
    }


//    @Override
//    public void onFragmentInteraction(Uri uri){
//
//    }
}