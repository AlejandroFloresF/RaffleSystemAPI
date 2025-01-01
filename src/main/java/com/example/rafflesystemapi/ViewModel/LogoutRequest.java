package com.example.rafflesystemapi.ViewModel;
import lombok.Data;

@Data
public class LogoutRequest {
    private String token;

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
