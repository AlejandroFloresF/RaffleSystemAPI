package com.example.rafflesystemapi.Ticket;

import com.example.rafflesystemapi.Raffle.Raffle;
import com.example.rafflesystemapi.User.User;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tickets")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer number;

    @ManyToOne
    @JoinColumn(name = "raffle_id", nullable = false)
    private Raffle raffle;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reserved_until")
    private LocalDateTime reservedUntil;

    private Boolean paid;
}
