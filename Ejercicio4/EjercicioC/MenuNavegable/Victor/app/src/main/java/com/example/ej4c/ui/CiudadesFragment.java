package com.example.ej4c.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ej4c.MainActivity;
import com.example.ej4c.R;
import com.example.ej4c.adapter.CiudadAdapter;
import com.example.ej4c.model.Ciudad;

import java.util.ArrayList;
import java.util.List;

public class CiudadesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_ciudades, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.recycler_ciudades);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setAdapter(new CiudadAdapter(getCiudades(), ciudad -> {

            Bundle bundle = new Bundle();
            bundle.putString("nombre", ciudad.getNombre());
            bundle.putString("habitantes", ciudad.getHabitantes());
            bundle.putString("distancia", ciudad.getDistanciaKm());
            bundle.putInt("imagen", ciudad.getImagenResId());

            Fragment detalleFragment = new DetalleCiudadFragment();
            detalleFragment.setArguments(bundle);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, detalleFragment)
                    .addToBackStack(null)
                    .commit();
        }));

        return view;
    }

    private List<Ciudad> getCiudades() {
        List<Ciudad> lista = new ArrayList<>();

        lista.add(new Ciudad("Buenos Aires", "3.12", "1161", R.drawable.bsas));
        lista.add(new Ciudad("Córdoba", "1.51", "685", R.drawable.cordoba));
        lista.add(new Ciudad("Rosario", "1.28", "296", R.drawable.rosario));
        lista.add(new Ciudad("Mendoza", "1.12", "1040", R.drawable.mendoza));
        lista.add(new Ciudad("San Miguel de Tucumán", "0.83", "1204", R.drawable.tucuman));
        lista.add(new Ciudad("Mar del Plata", "0.68", "400", R.drawable.mar_de_plata));
        lista.add(new Ciudad("Salta", "0.60", "1573", R.drawable.salta));

        return lista;
    }

    @Override
    public void onResume() {
        super.onResume();

        MainActivity mainActivity = (MainActivity) requireActivity();
        ActionBar actionBar = mainActivity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }

        mainActivity.getToggle().setDrawerIndicatorEnabled(true);
        mainActivity.getToggle().syncState();

        mainActivity.getToggle().setToolbarNavigationClickListener(null);
    }
}