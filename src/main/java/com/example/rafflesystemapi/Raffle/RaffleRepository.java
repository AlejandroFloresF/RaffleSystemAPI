package com.example.rafflesystemapi.Raffle;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RaffleRepository extends JpaRepository<Raffle, Long> {
    Optional<Raffle> findRaffleByName(String name);
    Optional<Raffle> findById(Long id);
}
