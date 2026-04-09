package com.example.ejerciciod.adaptadores;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.TintTypedArray;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ejerciciod.R;
import com.example.ejerciciod.VerActivity;
import com.example.ejerciciod.entidades.Contacto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListaContactoAdapter extends RecyclerView.Adapter<ListaContactoAdapter.ContactoViewHolder> {

    ArrayList<Contacto> listaContactos;
    ArrayList<Contacto> listaOriginal;

    public ListaContactoAdapter(ArrayList<Contacto> listaContactos) {
        this.listaContactos = listaContactos;
        listaOriginal = new ArrayList<>();
        listaOriginal.addAll(listaContactos);
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

    @SuppressLint("NotifyDataSetChanged")
    public void filtrado(String txtBuscar) {
        int longitud = txtBuscar.length();
        if(longitud == 0) {
            listaContactos.clear();
            listaContactos.addAll(listaOriginal);
        } else {
            String query = txtBuscar.toLowerCase();

            List<Contacto> collection = listaOriginal.stream()
                    .filter(c ->
                            (c.getNombre() != null && c.getNombre().toLowerCase().contains(query)) ||
                            (c.getTelefono() != null && c.getTelefono().toLowerCase().contains(query)) ||
                            (c.getCorreo_electronico() != null && c.getCorreo_electronico().toLowerCase().contains(query))
                    )
                    .collect(Collectors.toList());
            listaContactos.clear();
            listaContactos.addAll(collection);
        }
        notifyDataSetChanged();
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

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerActivity.class);
                    intent.putExtra("ID", listaContactos.get(getAbsoluteAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
