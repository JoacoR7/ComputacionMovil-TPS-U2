package com.example.video1.interfaces;

import com.example.video1.model.Pokedex;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface requests {
    @GET("pokemon/{id}")
    Call<Pokedex> request(@Path("id") String id);
}
