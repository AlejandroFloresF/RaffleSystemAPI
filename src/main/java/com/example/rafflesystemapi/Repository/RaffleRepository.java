package com.example.rafflesystemapi.Repository;

import com.example.rafflesystemapi.ViewModel.Raffle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RaffleRepository extends JpaRepository<Raffle, Long> {
    Optional<Raffle> findRaffleByName(String name);
    Optional<Raffle> findById(Long id);
}
