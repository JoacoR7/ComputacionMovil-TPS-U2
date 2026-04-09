package com.example.ej3_b;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.ej3_b.activities.CreateActivity;
import com.example.ej3_b.adapters.ProductsAdapter;
import com.example.ej3_b.interfaces.CRUDInterface;
import com.example.ej3_b.model.Product;
import com.example.ej3_b.utils.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    List<Product> products;
    CRUDInterface crudInterface;
    ListView lvProducts;

    FloatingActionButton btnCrear;

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
        lvProducts = findViewById(R.id.lvProducts);

        btnCrear = findViewById(R.id.btnCreate);
        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callCreate();
            }
        });

        getAll();
    }

    private void callCreate() {
        Intent intent = new Intent(getApplicationContext(), CreateActivity.class);
        startActivity(intent);
    }

    private void getAll(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crudInterface = retrofit.create(CRUDInterface.class);
        Call<List<Product>> call = crudInterface.getAll();
        call.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), response.message(),Toast.LENGTH_LONG).show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                products = response.body();
                ProductsAdapter productsAdapter = new ProductsAdapter(products, getApplicationContext());
                lvProducts.setAdapter(productsAdapter);
                products.forEach(p -> Log.i("Productos: ",p.toString()));
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(),Toast.LENGTH_LONG).show();
                Log.e("Response err: ",t.getMessage());
            }
        });

    }


}