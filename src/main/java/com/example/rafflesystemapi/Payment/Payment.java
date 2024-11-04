package com.example.rafflesystemapi.Payment;

import com.example.rafflesystemapi.Raffle.Raffle;
import com.example.rafflesystemapi.User.User;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "raffle_id", nullable = false)
    private Raffle raffle;

    private LocalDateTime date;

    private BigDecimal amount;

    private String status;
}
