package com.example.rafflesystemapi.ViewModel;

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
    private User userVM;
    @Column(name = "reserved_until")
    private LocalDateTime reservedUntil;
    private Boolean paid;


    public Ticket() {

    }
    public Ticket(Long id, Integer number, Raffle raffle, User userVM, LocalDateTime reservedUntil, Boolean paid) {
        this.id = id;
        this.number = number;
        this.raffle = raffle;
        this.userVM = userVM;
        this.reservedUntil = reservedUntil;
        this.paid = paid;
    }

    public Ticket(Integer number, Raffle raffle, User userVM, LocalDateTime reservedUntil, Boolean paid) {
        this.number = number;
        this.raffle = raffle;
        this.userVM = userVM;
        this.reservedUntil = reservedUntil;
        this.paid = paid;
    }
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Raffle getRaffle() {
        return raffle;
    }

    public void setRaffle(Raffle raffle) {
        this.raffle = raffle;
    }

    public User getUser() {
        return userVM;
    }

    public void setUser(User userVM) {
        this.userVM = userVM;
    }

    public LocalDateTime getReservedUntil() {
        return reservedUntil;
    }

    public void setReservedUntil(LocalDateTime reservedUntil) {
        this.reservedUntil = reservedUntil;
    }

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }
}
