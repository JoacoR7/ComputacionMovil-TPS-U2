package com.example.ejerciciob;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.ejerciciob.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        solicitarPermisos();
        MainActivityViewModel mv = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        mv.getMLocation().observe(this, new Observer<Location>(){
            @Override
            public void onChanged(Location location) {
                if(location != null){
                    double latitud = location.getLatitude();
                    double longitud = location.getLongitude();
                    binding.tvMostrar.setText("Latitud: " + latitud + " Longitud: " + longitud);
                    Log.d("salida latitud", latitud + "");
                    Log.d("salida longitud", longitud + "");
                }
            }
        });

        //Utiliza la libreria de google para obtener la ubicación
        //mv.obtenerUbicacion(); //lo obtiene solo una vez
        //mv.actualizarPosiciones(); //lo obtiene cada x tiempo
        //Utiliza el manager de location para obtener la ubicación
        mv.leerPosiciones();
    }

    public void solicitarPermisos(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
            && (checkSelfPermission(ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                || (checkSelfPermission(ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)){
            requestPermissions(new String[]{ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION}, 1000);

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // EL USUARIO DIO PERMISO: Ahora sí pedimos la ubicación
                MainActivityViewModel mv = new ViewModelProvider(this).get(MainActivityViewModel.class);
                mv.obtenerUbicacion();
            } else {
                Log.e("Permisos", "El usuario rechazó los permisos");
            }
        }
    }


}
