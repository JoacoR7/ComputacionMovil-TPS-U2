package com.example.ej3_b.activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ej3_b.R;
import com.example.ej3_b.interfaces.CRUDInterface;
import com.example.ej3_b.model.Product;
import com.example.ej3_b.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity {

    TextView txvPrice, txvName, txvIdProduct;
    CRUDInterface crudInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txvName = findViewById(R.id.txvName);
        txvPrice = findViewById(R.id.txvPrice);
        txvIdProduct = findViewById(R.id.txvIdProduct);
        int id = getIntent().getExtras().getInt("id");
        getOne(id);
    }

    private void getOne(int id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crudInterface = retrofit.create(CRUDInterface.class);
        Call<Product> call = crudInterface.getOne(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), response.message(),Toast.LENGTH_LONG).show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                Product product = response.body();
                txvIdProduct.setText(String.valueOf(product.getId()));
                txvName.setText(product.getName());
                txvPrice.setText(String.valueOf(product.getPrice()));

            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();
                Log.e("Response err: ",t.getMessage());
            }
        });

    }
}