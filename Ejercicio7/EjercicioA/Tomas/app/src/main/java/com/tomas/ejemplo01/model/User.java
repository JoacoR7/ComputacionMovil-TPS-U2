package com.tomas.ejemplo01.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("cuenta")
    private String user;
    private String contrasenia = null;
    @SerializedName("rol")
    private Rol rol;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
}
