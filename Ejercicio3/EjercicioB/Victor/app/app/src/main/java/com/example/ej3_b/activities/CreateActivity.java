package com.example.ej3_b.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

import com.example.ej3_b.MainActivity;
import com.example.ej3_b.R;
import com.example.ej3_b.dto.ProductDTO;
import com.example.ej3_b.interfaces.CRUDInterface;
import com.example.ej3_b.model.Product;
import com.example.ej3_b.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateActivity extends AppCompatActivity {

    EditText etName, etPrice;
    Button btnAceptar;
    CRUDInterface crudInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_create);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        etName = findViewById(R.id.etName);
        etPrice = findViewById(R.id.etPrice);
        btnAceptar = findViewById(R.id.btnAceptar);

        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnAceptar.setEnabled(btnEnabled());
            }
        });

        etPrice.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                btnAceptar.setEnabled(btnEnabled());
            }
        });


        btnAceptar.setEnabled(btnEnabled());

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProductDTO productDTO = new ProductDTO(etName.getText().toString(), Integer.valueOf(etPrice.getText().toString()));
                create(productDTO);
            }
        });

    }

    private void create(ProductDTO productDTO){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crudInterface = retrofit.create(CRUDInterface.class);
        Call<Product> call = crudInterface.create(productDTO);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), response.message(),Toast.LENGTH_LONG).show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                Product product = response.body();
                Toast.makeText(getApplicationContext(), product.getName() + " created",Toast.LENGTH_LONG).show();
                callMain();
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();
                Log.e("Response err: ",t.getMessage());
            }
        });
    }

    private void callMain() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

    private boolean btnEnabled(){
        return !etName.getText().toString().trim().isEmpty() && !etPrice.getText().toString().trim().isEmpty();
    }
}