package com.example.rafflesystemapi.Controller;

import com.example.rafflesystemapi.Service.AuthService;
import com.example.rafflesystemapi.ViewModel.LoginRequest;
import com.example.rafflesystemapi.ViewModel.AuthResponse;
import com.example.rafflesystemapi.ViewModel.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse token = authService.login(loginRequest);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest registerRequest) {
        AuthResponse token = authService.register(registerRequest);
        return ResponseEntity.ok(token);
    }
}
