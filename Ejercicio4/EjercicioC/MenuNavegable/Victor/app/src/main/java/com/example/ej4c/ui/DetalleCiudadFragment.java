package com.example.ej4c.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.ej4c.MainActivity;
import com.example.ej4c.R;

public class DetalleCiudadFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_ciudad, container, false);

        TextView nombre = view.findViewById(R.id.tv_nombre);
        TextView habitantes = view.findViewById(R.id.tv_habitantes);
        TextView distancia = view.findViewById(R.id.tv_distancia);
        ImageView imagen = view.findViewById(R.id.iv_ciudad);

        Bundle args = getArguments();

        if (args != null) {
            nombre.setText(args.getString("nombre"));
            habitantes.setText("Habitantes: " + args.getString("habitantes") + " millones");
            distancia.setText("Distancia: " + args.getString("distancia") + " km");
            imagen.setImageResource(args.getInt("imagen"));
        }

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity mainActivity = (MainActivity) requireActivity();
        ActionBar actionBar = mainActivity.getSupportActionBar();

        if (actionBar != null) {
            // Mostrar el botón de retroceso
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }

        // Deshabilitar el ícono del drawer
        mainActivity.getToggle().setDrawerIndicatorEnabled(false);
        mainActivity.getToggle().syncState();

        // Configurar el listener del botón de navegación
        mainActivity.getToggle().setToolbarNavigationClickListener(v -> {
            requireActivity().getSupportFragmentManager().popBackStack();
        });
    }

    @Override
    public void onPause() {
        super.onPause();

        MainActivity mainActivity = (MainActivity) requireActivity();
        // Limpiar el listener personalizado
        mainActivity.getToggle().setToolbarNavigationClickListener(null);
    }
}