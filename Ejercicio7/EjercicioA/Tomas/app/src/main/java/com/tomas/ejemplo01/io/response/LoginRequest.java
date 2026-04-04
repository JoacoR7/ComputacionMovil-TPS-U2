package com.tomas.ejemplo01.io.response;

public class LoginRequest {
    private String clave;
    private String cuenta;

    public LoginRequest(String cuenta, String clave) {
        this.cuenta = cuenta;
        this.clave = clave;
    }
}
