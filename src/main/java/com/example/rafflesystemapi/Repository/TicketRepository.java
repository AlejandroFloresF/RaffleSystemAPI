package com.example.rafflesystemapi.Repository;

import com.example.rafflesystemapi.ViewModel.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    Optional<Ticket> findTicketByNumber(Integer Number);
    @Query("SELECT t.number FROM Ticket t WHERE t.raffle.id = :raffleId")
    List<Integer> findPurchasedTicketsByRaffleId(@Param("raffleId") Long raffleId);
}

