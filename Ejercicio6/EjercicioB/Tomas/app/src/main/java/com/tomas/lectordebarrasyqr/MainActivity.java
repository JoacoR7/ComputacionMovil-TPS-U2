package com.tomas.lectordebarrasyqr;

import android.app.ComponentCaller;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class MainActivity extends AppCompatActivity {

    private Button btnGenerar, btnEscanear;
    private ImageView imgCodigoQR;
    private EditText txtCodigo;
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

        btnGenerar = findViewById(R.id.btnGenerar);
        btnEscanear = findViewById(R.id.btnEscanear);
        imgCodigoQR = findViewById(R.id.ivCodigoQr);
        txtCodigo = findViewById(R.id.etCodigo);

        btnGenerar.setOnClickListener(v -> {
            try {
                BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                Bitmap bitmap = barcodeEncoder.encodeBitmap(txtCodigo.getText().toString(), BarcodeFormat.QR_CODE, 800, 800);
                imgCodigoQR.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        btnEscanear.setOnClickListener(v ->  {
            IntentIntegrator integrator = new IntentIntegrator(MainActivity.this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
            integrator.setPrompt("Lectura de códigos");
            integrator.setCameraId(0);

            integrator.setOrientationLocked(false);
            integrator.setCaptureActivity(CapturaVertical.class);

            integrator.setBeepEnabled(true);
            integrator.setBarcodeImageEnabled(true);
            integrator.initiateScan();
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data, @NonNull ComponentCaller caller) {


        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null) {
            if (intentResult.getContents() == null) {
                Toast.makeText(this, "Lectura Cancelada", Toast.LENGTH_SHORT).show();
            }
            else {
                txtCodigo.setText(intentResult.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data, caller);
        }

    }
}