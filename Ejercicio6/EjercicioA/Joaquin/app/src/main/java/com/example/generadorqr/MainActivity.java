package com.example.generadorqr;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    EditText txtDatos;
    Button btnGenerar;
    ImageView qrCode;

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

        txtDatos = findViewById(R.id.txtDatos);
        btnGenerar = findViewById(R.id.btnGenerar);
        qrCode = findViewById(R.id.qrCode);

        btnGenerar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    /*
                    tel: número
                    Bitmap bitmap = barcodeEncoder.encodeBitmap("tel:"+txtDatos.getText().toString(), BarcodeFormat.QR_CODE, 750, 750);

                    SMSTO: número: mensaje
                    Bitmap bitmap = barcodeEncoder.encodeBitmap("SMSTO:1234:"+txtDatos.getText().toString(), BarcodeFormat.QR_CODE, 750, 750);

                    WIFI:SSID:CDP;T:WEP;P:password;
                    Bitmap bitmap = barcodeEncoder.encodeBitmap("WIFI:SSID:CDP;T:WEP;P:1234;"+txtDatos.getText().toString(), BarcodeFormat.QR_CODE, 750, 750);
                     */
                    Bitmap bitmap = barcodeEncoder.encodeBitmap(txtDatos.getText().toString(), BarcodeFormat.QR_CODE, 750, 750);
                    qrCode.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}