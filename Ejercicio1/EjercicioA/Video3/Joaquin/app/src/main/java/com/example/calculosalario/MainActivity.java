package com.example.calculosalario;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    EditText etSalario;
    TextView tvIsss, tvAfp, tvRenta, tvLiquido;
    Button btnCalcular, btnNuevo;

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

        // Input
        etSalario = findViewById(R.id.etSalario);

        // View
        tvIsss = findViewById(R.id.tvIsss);
        tvAfp = findViewById(R.id.tvAfp);
        tvRenta = findViewById(R.id.tvRenta);
        tvLiquido = findViewById(R.id.tvLiquido);

        // Botones
        btnCalcular = findViewById(R.id.btnCalcular);
        btnNuevo = findViewById(R.id.btnNuevo);

        btnCalcular.setEnabled(false);

        etSalario.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                btnCalcular.setEnabled(!s.toString().isBlank());
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }
        });

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float salario = 0.0f;
                try {
                    salario = Float.parseFloat(etSalario.getText().toString());
                } catch (NumberFormatException e) {
                    showToast("Debe ingresar un número válido");
                }

                if (salario <= 0) {
                    showToast("Debe ingresar un número válido");
                } else {
                    DecimalFormat df = new DecimalFormat("#.00");
                    double isss = salario * 0.03;
                    double afp = salario * 0.0725;
                    double renta = salario * 0.0528375;
                    double salarioLiquido = salario - isss - afp - renta;

                    tvIsss.setText(String.valueOf(df.format(isss)));
                    tvAfp.setText(String.valueOf(df.format(afp)));
                    tvRenta.setText(String.valueOf(df.format(renta)));

                    tvLiquido.setText(String.valueOf(df.format(salarioLiquido)));
                }
            }
        });

        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etSalario.setText("");
                tvIsss.setText(getText(R.string.isss_3));
                tvAfp.setText(getText(R.string.afp_7_25));
                tvRenta.setText(getText(R.string.renta));
                tvLiquido.setText(getText(R.string.liquido_a_recibir));
            }
        });
    }

    public void showToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}