package com.example.rafflesystemapi.exception;

public class RaffleNotFoundException extends RuntimeException {
    public RaffleNotFoundException(String message) {
        super(message);
    }
}