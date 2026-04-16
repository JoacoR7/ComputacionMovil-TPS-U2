package com.example.ej4c.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ej4c.R;

public class HomeFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_simple, container, false);

        TextView title    = view.findViewById(R.id.tv_fragment_title);
        TextView subtitle = view.findViewById(R.id.tv_fragment_subtitle);

        title.setText("🏠 Home");
        subtitle.setText("Bienvenido a la pantalla principal.\nDesde aquí podés navegar\ncon el menú lateral.");

        return view;
    }
}