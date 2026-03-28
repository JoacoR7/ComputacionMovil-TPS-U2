package com.tomas.tutorialcrud.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomas.tutorialcrud.R;
import com.tomas.tutorialcrud.entidades.Cliente;

import java.util.ArrayList;

public class AdapterClientes extends RecyclerView.Adapter<AdapterClientes.ViewHolderClientes> {

    ArrayList<Cliente> listClientes;

    public AdapterClientes(ArrayList<Cliente> listClientes) {
        this.listClientes = listClientes;
    }

    @Override
    public ViewHolderClientes onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_clientes, null, false);
        return new ViewHolderClientes(vista);
    }

    @Override
    public void onBindViewHolder(ViewHolderClientes holder, int position) {
        final Cliente item = listClientes.get(position);
        holder.id.setText(String.valueOf(listClientes.get(position).getIdCliente()));
        holder.nombre.setText(listClientes.get(position).getNombre());
        holder.apellido.setText(listClientes.get(position).getApellido());
        holder.documento.setText(listClientes.get(position).getDocumento());
    }

    @Override
    public int getItemCount() {
        return listClientes.size();
    }

    //Aca se controlan los elementos del layout
    public class ViewHolderClientes extends RecyclerView.ViewHolder {
        TextView id, nombre, apellido, documento;
        public ViewHolderClientes(View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.txtIdList);
            nombre = itemView.findViewById(R.id.txtNomList);
            apellido = itemView.findViewById(R.id.txtApeList);
            documento = itemView.findViewById(R.id.txtDocList);
        }
    }
}
