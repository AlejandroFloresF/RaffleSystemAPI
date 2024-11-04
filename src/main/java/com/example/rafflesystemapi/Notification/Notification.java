package com.example.rafflesystemapi.Notification;

import com.example.rafflesystemapi.Raffle.Raffle;
import com.example.rafflesystemapi.User.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "notifications")
public class Notification {
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

    private String type;

    private String message;
}
