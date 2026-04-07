package com.example.ej2_f;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ej2_f.databinding.ActivityMainBinding;
import com.example.ej2_f.databinding.ActivityMostrarBinding;

public class MostrarActivity extends AppCompatActivity {

    private MostrarActivityViewModel mv;
    private ActivityMostrarBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MostrarActivityViewModel.class);
        binding = ActivityMostrarBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mv.getProducto().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMostrar.setText(s);
            }
        });

        mv.leerDatos();

    }
}