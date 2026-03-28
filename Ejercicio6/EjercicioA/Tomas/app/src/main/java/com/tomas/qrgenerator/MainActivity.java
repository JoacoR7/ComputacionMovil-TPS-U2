package com.tomas.qrgenerator;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
        EditText txtDatos = findViewById(R.id.txtDatos);
        Button btnGenera = findViewById(R.id.btnGenera);
        ImageView imgQr = findViewById(R.id.qrCode);
        btnGenera.setOnClickListener(v -> {
            try {
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();

                /*
                Para telefono sería:
                "tel:"+txtDatos.getText().toString()

                Para SMS:
                SMSTO:(String con número):(String con mensaje)

                Para WIFI:
                WIFI:S:(SSID (Nombre de red));T:(Tipo de seguridad);P:(Contrasenia)
                Ejemplo:
                WIFI:S:CDP;T:WEP;P:12345678
                 */
                Bitmap bitmap = barcodeEncoder.encodeBitmap(txtDatos.getText().toString(), BarcodeFormat.QR_CODE, 750, 750);
                imgQr.setImageBitmap(bitmap);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}