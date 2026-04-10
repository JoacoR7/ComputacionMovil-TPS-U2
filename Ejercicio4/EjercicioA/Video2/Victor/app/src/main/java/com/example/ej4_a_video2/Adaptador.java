package com.example.ej4_a_video2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.viewHolder>{

    ArrayList<Pais> listaPaises;

    public Adaptador(ArrayList<Pais> listaPaises) {
        this.listaPaises = listaPaises;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_hija, parent, false);
        return new viewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.pais.setText(listaPaises.get(position).getNombre());
        holder.capital.setText(listaPaises.get(position).getCapital());
        holder.habitantes.setText(listaPaises.get(position).getPoblacion());
        holder.region.setText(listaPaises.get(position).getRegion());
        holder.bandera.setImageResource(listaPaises.get(position).getBandera());
    }

    @Override
    public int getItemCount() {
        return listaPaises.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView pais,capital,habitantes,region;
        ImageView bandera;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            pais = itemView.findViewById(R.id.pais);
            capital = itemView.findViewById(R.id.capital);
            habitantes = itemView.findViewById(R.id.habitantes);
            region = itemView.findViewById(R.id.region);
            bandera = itemView.findViewById(R.id.bandera);

        }
    }
}
