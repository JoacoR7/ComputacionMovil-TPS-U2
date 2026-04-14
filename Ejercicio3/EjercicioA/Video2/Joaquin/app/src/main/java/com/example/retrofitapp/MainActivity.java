package com.example.retrofitapp;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

    interface RequestUser {
        @GET("/posts/{id}")
        Call<Post> getBook(@Path("id") String id);

        @POST("/posts")
        Call<Post> createBook(@Body Post post);
    }
    TextView tvResponse, tvResponse2;

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

        tvResponse = findViewById(R.id.tvResponse);
        tvResponse2 = findViewById(R.id.tvResponse2);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RequestUser requestUser = retrofit.create(RequestUser.class);

        requestUser.getBook("3").enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                StringBuilder sb = new StringBuilder();
                Post post = response.body();
                sb.append("UserId: ").append(post.getUserId()).append("\n");
                sb.append("Id: ").append(post.getId()).append("\n");
                sb.append("Title: ").append(post.getTitle()).append("\n");
                sb.append("Body: ").append(post.getBody()).append("\n");
                tvResponse.setText(sb.toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvResponse.setText(t.getMessage());
            }
        });

        Post post = new Post("77", "Libro de prueba", "Body de prueba");

        requestUser.createBook(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                StringBuilder sb = new StringBuilder();
                Post post = response.body();
                sb.append("Title: ").append(post.getUserId()).append("\n");
                sb.append("Id: ").append(post.getId()).append("\n");
                sb.append("Title: ").append(post.getTitle()).append("\n");
                sb.append("Body: ").append(post.getBody()).append("\n");
                tvResponse2.setText(sb.toString());
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {
                tvResponse2.setText(t.getMessage());
            }
        });
    }
}