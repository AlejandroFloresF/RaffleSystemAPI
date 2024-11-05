package com.example.rafflesystemapi.Raffle;

import com.example.rafflesystemapi.User.User;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Entity
@Table(name = "raffles")
public class Raffle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private String prize;

    @Column(name = "ticket_cost")
    private BigDecimal ticketCost;

    @Column(name = "start_date")
    private LocalDateTime startDate;

    @Column(name = "end_date")
    private LocalDateTime endDate;

    @Transient
    private int antiquity;

    private String status;

    @ManyToOne
    @JoinColumn(name = "winner_id")
    private User winner;

    public Raffle() {
    }

    public Raffle(Long id, User winner, String status, LocalDateTime endDate, LocalDateTime startDate, BigDecimal ticketCost, String prize, String description, String name) {
        this.id = id;
        this.winner = winner;
        this.status = status;
        this.endDate = endDate;
        this.startDate = startDate;
        this.ticketCost = ticketCost;
        this.prize = prize;
        this.description = description;
        this.name = name;
    }

    public Raffle(String name, String description, String prize, BigDecimal ticketCost, LocalDateTime endDate, LocalDateTime startDate, String status, User winner) {
        this.name = name;
        this.description = description;
        this.prize = prize;
        this.ticketCost = ticketCost;
        this.endDate = endDate;
        this.startDate = startDate;
        this.status = status;
        this.winner = winner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public BigDecimal getTicketCost() {
        return ticketCost;
    }

    public void setTicketCost(BigDecimal ticketCost) {
        this.ticketCost = ticketCost;
    }

    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAntiquity(int antiquity) {
        this.antiquity = antiquity;
    }

    public int getAntiquity() {
        return Period.between(this.endDate.toLocalDate(), LocalDate.now()).getYears();
    }
}
