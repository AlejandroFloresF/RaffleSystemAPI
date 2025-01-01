package com.example.rafflesystemapi.Service;

import com.example.rafflesystemapi.Repository.UserRepository;
import com.example.rafflesystemapi.ViewModel.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final RedisTemplate<String, String> redisTemplate;

    public AuthResponse login(LoginRequest loginRequest) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        UserDetails user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        User userId = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow();
        String token = jwtService.getToken(user, userId.getId());
        return AuthResponse.builder()
                .token(token)
                .build();
    }

    public AuthResponse logout(LogoutRequest logoutRequest) {
        String token = logoutRequest.getToken();
        redisTemplate.opsForValue().set(token, "banned", tokenExpiration, TimeUnit.SECONDS);
    }

    public boolean isTokenBanned(String token) {
        return redisTemplate.hasKey(token);
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .fullName(registerRequest.getFullName())
                .phone(registerRequest.getPhone())
                .email(registerRequest.getEmail())
                .address(registerRequest.getAddress())
                .birthDate(registerRequest.getBirthDate())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .username(registerRequest.getUsername())
                .role(Role.USER)
                .isAdmin(false)
                .receivesNotifications(true).build();
        userRepository.save(user);

        User userId = userRepository.findByUsername(registerRequest.getUsername()).orElseThrow();
        return AuthResponse.builder()
                .token(jwtService.getToken(user, userId.getId()))
                .build();
    }
}
