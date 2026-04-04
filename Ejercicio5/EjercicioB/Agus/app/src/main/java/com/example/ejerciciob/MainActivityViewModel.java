package com.example.ejerciciob;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.util.List;


public class MainActivityViewModel extends AndroidViewModel {
    private FusedLocationProviderClient fused;
    private MutableLiveData<Location> mLocation;

    private LocationManager manager;
    private Context context;
    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.context = application.getApplicationContext();
        this.fused = LocationServices.getFusedLocationProviderClient(context);
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        this.mLocation = new MutableLiveData<>();
    }

    public LiveData<Location> getMLocation(){
        if(mLocation == null){
            mLocation = new MutableLiveData<>();
        }
        return mLocation;
    }


    public void obtenerUbicacion(){

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> tarea = fused.getLastLocation();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            tarea.addOnSuccessListener(context.getMainExecutor(), new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if(location != null){
                        mLocation.postValue(location);
                    }
                }
            });
        }
    }
    public void actualizarPosiciones(){
        LocationRequest locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(5000);

        LocationCallback locationCallback  = new LocationCallback(){
            @Override
            public void onLocationResult(@NonNull LocationResult locationResult) {
                if (locationResult == null){
                    return;
                }
                Location ultimaLocalizacion = locationResult.getLastLocation();
                mLocation.postValue(ultimaLocalizacion);
            }
        };
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fused.requestLocationUpdates(locationRequest, locationCallback, null);
    }

    public void leerPosiciones(){
        ListenerPosicion listener = new ListenerPosicion();
        long tiempo = 5000;
        float distancia = 10.00f;
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, tiempo, distancia, listener);
    }
    class ListenerPosicion implements LocationListener {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            mLocation.postValue(location);
        }
    }
}
