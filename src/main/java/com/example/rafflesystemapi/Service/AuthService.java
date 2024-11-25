package com.example.rafflesystemapi.Service;

import com.example.rafflesystemapi.dto.AuthRequest;

public class AuthService {
    public String authenticate(AuthRequest authRequest) {
        return "token-de-ejemplo";
    }

    public void register(AuthRequest authRequest) {
    }
}
