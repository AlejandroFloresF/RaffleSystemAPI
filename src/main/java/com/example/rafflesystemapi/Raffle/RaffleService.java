package com.example.rafflesystemapi.Raffle;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RaffleService {
    public List<Raffle> getAllRaffles() {
        List<Raffle> raffles = new ArrayList<>();

        raffles.add(new Raffle(
                "Sample Raffle",
                "Test raffle description",
                "360-degree Camera",
                BigDecimal.valueOf(18.23),
                LocalDateTime.now().plusDays(5), // Example end date
                LocalDateTime.now(), // Example start date
                "in progress",
                null // Assuming no winner initially
        ));

        raffles.add(new Raffle(
                "Second Raffle",
                "Another raffle description",
                "Smartphone",
                BigDecimal.valueOf(25.50),
                LocalDateTime.now().plusDays(10),
                LocalDateTime.now().plusDays(2),
                "upcoming",
                null
        ));

        return raffles;
    }
}
