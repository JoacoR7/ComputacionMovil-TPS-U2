package com.example.video4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<Modelo> {
    private List<Modelo> modeloList;
    private Context modeloContext;
    private int resourceLayout;
    public Adapter(@NonNull Context context, int resource, List<Modelo> objects) {
        super(context, resource, objects);
        this.modeloList = objects;
        this.modeloContext = context;
        this.resourceLayout = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(modeloContext).inflate(R.layout.lugares, null);
        }

        Modelo modelo = modeloList.get(position);

        ImageView imageView = view.findViewById(R.id.imvLugar);
        imageView.setImageResource(modelo.getImagen());

        TextView titulo = view.findViewById(R.id.txvTitulo);
        titulo.setText(modelo.getTitulo());

        TextView descripcion = view.findViewById(R.id.txvDescripcion);
        descripcion.setText(modelo.getDescripcion());


        return view;
    }
}
