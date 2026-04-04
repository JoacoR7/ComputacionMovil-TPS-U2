package com.tomas.ejemplo01;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.tomas.ejemplo01.io.ApiService;
import com.tomas.ejemplo01.io.ApiServiceFactory;
import com.tomas.ejemplo01.io.response.LoginRequest;
import com.tomas.ejemplo01.io.response.LoginResponse;
import com.tomas.ejemplo01.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText nombre, apellido;
    Button btnEnviar, btnPrueba;

    private SharedPreferences prefs;
    private ApiService apiService;

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

        apiService = ApiServiceFactory.create(getBaseContext());
        //Inicializamos
        btnEnviar = findViewById(R.id.btnEnviar);
        nombre = findViewById(R.id.textNombre);
        apellido = findViewById(R.id.textApellido);
        btnPrueba = findViewById(R.id.btnPrueba);

        btnPrueba.setOnClickListener(v -> {
            testearUsuarioConToken();
        });

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nombre.getText().toString().isEmpty()) {
                    nombre.setError("Debe ingresar el nombre");
                }
                else {
                    if (apellido.getText().toString().isEmpty()) {
                        apellido.setError("Debe ingresar el apellido");
                    }
                    else {
                        performLogin();
                    }
                }
            }
        });
    }

    private void createSessionPreference(String jwt) {
        prefs = getSharedPreferences("login_ej", MODE_PRIVATE);
        prefs.edit().putString("token", jwt).apply();
        apiService = ApiServiceFactory.create(getBaseContext());
    }

    private void testearUsuarioConToken() {
        Call<User> call = apiService.buscarUsuario("tomas2");
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    User user = response.body();
                    if (user == null || user.getUser() == null || user.getUser().isEmpty()) {
                        Toast.makeText(getBaseContext(), "El usuario no existe", Toast.LENGTH_LONG).show();
                    }
                    else {
                        Toast.makeText(getBaseContext(), "Usuario: " + user.getUser() + " Rol: " + user.getRol().toString(), Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getBaseContext(), "No esta autenticado", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
    private void performLogin() {
        //SE UTILIZA EL ATRIBUTO APELLIDO COMO CONTRASENIA (AL HABER REUTILIZADO UN EJEMPLO ANTERIOR)
        Call<LoginResponse> call = apiService.postLogin(new LoginRequest(nombre.getText().toString(), apellido.getText().toString()));

        call.enqueue(new Callback<LoginResponse>() {

            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginReponse = response.body();
                    if (loginReponse == null) {
                        Toast.makeText(getBaseContext(), "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    if (loginReponse.getJwt() != null && !loginReponse.getJwt().isEmpty()) {

                        createSessionPreference(loginReponse.getJwt());
                        Toast.makeText(getBaseContext(), "Logueado correctamente", Toast.LENGTH_LONG).show();
                    } else {
                        Log.i("API_RESPONSE", "Éxito: " + loginReponse.toString());
                        Toast.makeText(getBaseContext(), "La contraseña es incorrecta", Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getBaseContext(), "La contraseña es incorrecta", Toast.LENGTH_LONG).show();
                }
             }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getBaseContext(), "Se produjo un error en el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}