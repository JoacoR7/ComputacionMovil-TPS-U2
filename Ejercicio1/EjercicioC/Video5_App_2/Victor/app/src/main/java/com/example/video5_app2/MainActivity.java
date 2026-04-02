package com.example.video5_app2;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private Button btnIniciar, btnDetener;
    private TextView txvMensaje;
    private EditText txtTexto;
    private volatile boolean running = false;
    private int suma = 0;
    private Thread hilo;

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

        btnIniciar = findViewById(R.id.btnIniciar);
        btnDetener = findViewById(R.id.btnDetener);
        txvMensaje = findViewById(R.id.txvMensaje);
        txtTexto = findViewById(R.id.txtTexto);

        txtTexto.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN){
                    txvMensaje.setText("Tecla presionada" + event.getKeyCode());
                    return true;
                }
                return false;
            }
        });

        // Acciones sobre el TextView, no sobre cualquier parte de la pantalla
        txvMensaje.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        txvMensaje.setText("Tocaste la pantalla");
                        break;
                    case MotionEvent.ACTION_MOVE:
                        txvMensaje.setText("Moviendo el dedo por la pantalla");
                        break;
                    case MotionEvent.ACTION_UP:
                        txvMensaje.setText("Levantaste el dedo de la pantalla");
                        break;
                }
                return true;
            }
        });

        btnIniciar.setOnClickListener(v -> {
            if (running) return;

            running = true;
            suma = 0;

            hilo = new Thread(() -> {
                for (int i = 0; i < 50000 && running; i++) {
                    suma += i;
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                runOnUiThread(() ->
                        txvMensaje.setText("La suma total fue: " + suma)
                );
            });

            hilo.start();
        });

        btnDetener.setOnClickListener(v -> {
            running = false;
            txvMensaje.setText("Hilo detenido. Suma: " + suma);
        });

    }
}