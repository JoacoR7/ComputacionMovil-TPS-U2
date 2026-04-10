package com.example.ej3_b.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.FragmentManager;

import com.example.ej3_b.MainActivity;
import com.example.ej3_b.R;
import com.example.ej3_b.fragments.DeleteFragment;
import com.example.ej3_b.fragments.DeleteInterface;
import com.example.ej3_b.interfaces.CRUDInterface;
import com.example.ej3_b.model.Product;
import com.example.ej3_b.utils.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailActivity extends AppCompatActivity implements DeleteInterface {

    TextView txvPrice, txvName, txvIdProduct;
    CRUDInterface crudInterface;
    Button btnEdit, btnDelete;
    Product product;

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
        btnDelete = findViewById(R.id.btnDelete);
        int id = getIntent().getExtras().getInt("id");
        getOne(id);

        btnEdit = findViewById(R.id.btnEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callEdit();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDeleteDialog(product.getId());
            }
        });
    }

    private void callEdit() {
        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }

    private void getOne(int id) {
        crudInterface = getCrudInterface();
        Call<Product> call = crudInterface.getOne(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), response.message(),Toast.LENGTH_LONG).show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                product = response.body();
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

    @Override
    public void showDeleteDialog(int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        DeleteFragment deleteFragment = new DeleteFragment("Delete product ", product.getId(), this);
        deleteFragment.show(fragmentManager, "Alert");
    }

    @Override
    public void delete(int id) {
        crudInterface = getCrudInterface();
        Call<Product> call = crudInterface.delete(id);
        call.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), response.message(),Toast.LENGTH_LONG).show();
                    Log.e("Response err: ", response.message());
                    return;
                }
                product = response.body();
                Toast.makeText(getApplicationContext(), product.getName() + " deleted!",Toast.LENGTH_LONG).show();
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

    private CRUDInterface getCrudInterface(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        crudInterface = retrofit.create(CRUDInterface.class);
        return crudInterface;
    }

}