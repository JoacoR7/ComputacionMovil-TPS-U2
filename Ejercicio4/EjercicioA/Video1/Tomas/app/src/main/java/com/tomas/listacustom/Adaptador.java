package com.tomas.listacustom;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

public class Adaptador extends ArrayAdapter<Fruta> {

    public Adaptador(@NonNull Context context, int resource, @NonNull List<Fruta> objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View vista, @NonNull ViewGroup parent) {
        Fruta fruta = getItem(position);
        if (vista == null) {
            vista = LayoutInflater.from(getContext()).inflate(R.layout.activity_custom, parent, false);
        }

        TextView titulo = vista.findViewById(R.id.titulo);
        TextView vitaminas = vista.findViewById(R.id.vitaminas);
        TextView color = vista.findViewById(R.id.color);
        TextView lugar = vista.findViewById(R.id.lugar);
        ImageView foto = vista.findViewById(R.id.foto);
        Button btnVer = vista.findViewById(R.id.btnVer);

        titulo.setText(fruta.getNombre());
        vitaminas.setText(fruta.getVitaminas());
        lugar.setText(fruta.getLugar());
        color.setBackgroundColor(fruta.getColor());
        foto.setImageResource(fruta.getFoto());

        Animation animation = AnimationUtils.loadAnimation(getContext(), R.anim.rotate);
        foto.startAnimation(animation);

        btnVer.setOnClickListener(v ->  {
            Intent intent = new Intent(getContext(), VerActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            intent.putExtra("keyFruta", fruta);
            ContextCompat.startActivity(getContext(), intent, null);
        });
        return vista;
    }
}
