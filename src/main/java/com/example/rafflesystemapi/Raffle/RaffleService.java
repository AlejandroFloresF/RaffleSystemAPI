package com.example.rafflesystemapi.Raffle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class RaffleService {
    private final RaffleRepository raffleRepository;

    @Autowired
    public RaffleService(RaffleRepository raffleRepository) {
        this.raffleRepository = raffleRepository;
    }
    public List<Raffle> getAllRaffles() {
        return this.raffleRepository.findAll();
    }

    public void newRaffle(Raffle raffle) {
        Optional<Raffle> res = raffleRepository.findRaffleByName(raffle.getName());
        if(res.isPresent()) {
            throw new IllegalStateException("Raffle with a same name already exists");
        }
        raffleRepository.save(raffle);
    }
}
