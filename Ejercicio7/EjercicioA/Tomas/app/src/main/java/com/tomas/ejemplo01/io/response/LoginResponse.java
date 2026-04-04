package com.tomas.ejemplo01.io.response;

import com.google.gson.annotations.SerializedName;
import com.tomas.ejemplo01.model.User;

public class LoginResponse {
    //private User user;
    @SerializedName("accessToken")
    private String jwt;

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
