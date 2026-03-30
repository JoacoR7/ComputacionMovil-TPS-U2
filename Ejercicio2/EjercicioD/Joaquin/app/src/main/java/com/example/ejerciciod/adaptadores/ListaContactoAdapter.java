package com.example.ejerciciod.adaptadores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciod.R;
import com.example.ejerciciod.entidades.Contacto;

import java.util.ArrayList;

public class ListaContactoAdapter extends RecyclerView.Adapter<ListaContactoAdapter.ContactoViewHolder> {

    ArrayList<Contacto> listaContactos;

    public ListaContactoAdapter(ArrayList<Contacto> listaContactos) {
        this.listaContactos = listaContactos;
    }

    @NonNull
    @Override
    public ListaContactoAdapter.ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_contacto, parent, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaContactoAdapter.ContactoViewHolder holder, int position) {
        holder.txtNombre.setText(listaContactos.get(position).getNombre());
        holder.txtTelefono.setText(listaContactos.get(position).getTelefono());
        holder.txtCorreo.setText(listaContactos.get(position).getCorreo_electronico());
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView txtNombre, txtTelefono, txtCorreo;
        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtNombre = itemView.findViewById(R.id.txtNombre);
            txtTelefono = itemView.findViewById(R.id.txtTelefono);
            txtCorreo = itemView.findViewById(R.id.txtCorreo);
        }
    }
}
