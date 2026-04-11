package com.tomas.listacustom;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ver);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ver), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView titulo1 = findViewById(R.id.titulo1);
        TextView vitaminas1 = findViewById(R.id.vitaminas1);
        TextView color1 = findViewById(R.id.color1);
        TextView lugar1 = findViewById(R.id.lugar1);
        ImageView foto1 = findViewById(R.id.foto1);
        Button btnVolver = findViewById(R.id.btnVolver);

        Fruta fruta = (Fruta) getIntent().getExtras().getSerializable("keyFruta");

        titulo1.setText(fruta.getNombre());
        vitaminas1.setText(fruta.getVitaminas());
        lugar1.setText(fruta.getLugar());
        color1.setBackgroundColor(fruta.getColor());
        foto1.setImageResource(fruta.getFoto());

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
            }
        });

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        foto1.startAnimation(animation);
    }
}