package com.example.navegacionentreactivities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InfoActivity extends AppCompatActivity {

    Button btnAnterior, btnSiguiente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_info);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnAnterior = findViewById(R.id.btnAnterior);
        btnSiguiente = findViewById(R.id.btnSiguiente);

        btnAnterior.setOnClickListener(this::onClick);
        btnSiguiente.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        Intent intent;

        if (view.getId() == R.id.btnAnterior) {
            intent = new Intent(InfoActivity.this, MainActivity.class);
            startActivity(intent);

        } else if (view.getId() == R.id.btnSiguiente) {
            intent = new Intent(InfoActivity.this, VideoActivity.class);
            startActivity(intent);
        }
    }
}