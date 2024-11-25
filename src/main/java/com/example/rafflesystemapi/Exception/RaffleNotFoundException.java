package com.example.rafflesystemapi.Exception;

public class RaffleNotFoundException extends RuntimeException {
    public RaffleNotFoundException(String message) {
        super(message);
    }
}