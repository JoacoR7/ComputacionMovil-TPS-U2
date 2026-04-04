package com.tomas.ejemplo01.io;

import com.tomas.ejemplo01.io.response.LoginRequest;
import com.tomas.ejemplo01.io.response.LoginResponse;
import com.tomas.ejemplo01.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @POST ("auth/login")
    public Call<LoginResponse> postLogin(@Body LoginRequest request);

    @GET("api/v1/usuario/buscar/{nombre}")
    public Call<User> buscarUsuario(@Path("nombre") String nombre);
}
