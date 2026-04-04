package com.example.video1;

import com.example.video1.interfaces.requests;
import com.example.video1.model.ModeloRetorno;
import com.example.video1.model.Pokedex;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConsultarApi {
    public static String URL = "https://pokeapi.co/api/v2/";
    public static Retrofit varRetro;
    ModeloRetorno modeloRetorno = new ModeloRetorno();

    public void respuesta(String id) {
        varRetro = new Retrofit.Builder().baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        requests apiRequests = varRetro.create(requests.class);

        Call<Pokedex> call = apiRequests.request(id);

        call.enqueue(new Callback<Pokedex>() {
            @Override
            public void onResponse(Call<Pokedex> call, Response<Pokedex> response) {
                try {
                    if (response.isSuccessful()) {
                        Pokedex pokedex = response.body();
                        modeloRetorno.setId(pokedex.getId());
                        modeloRetorno.setName(pokedex.getName());
                        modeloRetorno.setHeight(pokedex.getHeight());
                        modeloRetorno.setWeight(pokedex.getWeight());
                        modeloRetorno.setFront_default(pokedex.getSprites().getFront_default());
                    } else {
                        System.out.println("Error");
                        System.out.println(call);
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<Pokedex> call, Throwable t) {
                System.out.println(t);
            }
        });
    }
}
