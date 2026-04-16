package com.example.ej4c.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ej4c.R;
import com.example.ej4c.model.Ciudad;

import java.util.List;

public class CiudadAdapter extends RecyclerView.Adapter<CiudadAdapter.CiudadViewHolder> {

    private final List<Ciudad> listaCiudades;
    private final OnCiudadClickListener listener;

    // Interfaz para manejar los clicks
    public interface OnCiudadClickListener {
        void onCiudadClick(Ciudad ciudad);
    }

    public CiudadAdapter(List<Ciudad> listaCiudades, OnCiudadClickListener listener) {
        this.listaCiudades = listaCiudades;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CiudadViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_ciudad, parent, false);
        return new CiudadViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CiudadViewHolder holder, int position) {
        Ciudad ciudad = listaCiudades.get(position);

        holder.tvNombre.setText(ciudad.getNombre());
        holder.tvHabitantes.setText("Habitantes: " + ciudad.getHabitantes() + " millones");
        holder.tvDistancia.setText("Distancia: " + ciudad.getDistanciaKm() + " km");
        holder.ivCiudad.setImageResource(ciudad.getImagenResId());

        // Configurar el click en toda la tarjeta
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCiudadClick(ciudad);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaCiudades.size();
    }

    // ViewHolder
    static class CiudadViewHolder extends RecyclerView.ViewHolder {
        ImageView ivCiudad;
        TextView tvNombre;
        TextView tvHabitantes;
        TextView tvDistancia;

        public CiudadViewHolder(@NonNull View itemView) {
            super(itemView);
            ivCiudad = itemView.findViewById(R.id.iv_ciudad);
            tvNombre = itemView.findViewById(R.id.tv_nombre);
            tvHabitantes = itemView.findViewById(R.id.tv_habitantes);
            tvDistancia = itemView.findViewById(R.id.tv_distancia);
        }
    }
}