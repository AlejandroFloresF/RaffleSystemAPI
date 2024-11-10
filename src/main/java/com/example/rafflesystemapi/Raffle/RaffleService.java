package com.example.rafflesystemapi.Raffle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RaffleService {

    HashMap<String, Object> statusMessage;

    private final RaffleRepository raffleRepository;

    @Autowired
    public RaffleService(RaffleRepository raffleRepository) {
        this.raffleRepository = raffleRepository;
    }
    public List<Raffle> getAllRaffles() {
        return this.raffleRepository.findAll();
    }

    public ResponseEntity<Object> newRaffle(Raffle raffle) {
        statusMessage = new HashMap<>();
        Optional<Raffle> res = raffleRepository.findRaffleByName(raffle.getName());
        statusMessage.put("name", raffle.getName());

        if(res.isPresent() && raffle.getId() == null) {
            statusMessage.put("Error: ", true);
            statusMessage.put("message", "Raffle already exists");
            return new ResponseEntity<>(
                    statusMessage,
                    HttpStatus.CONFLICT
            );
        }
        statusMessage.put("message", "Raffle created successfully");
        if(raffle.getId() > 0) {
            statusMessage.put("message", "Raffle successfully updated");
        }
        raffleRepository.save(raffle);
        statusMessage.put("Data: ", raffle);

        return new ResponseEntity<>(
                statusMessage,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteRaffle(Long id) {
        statusMessage = new HashMap<>();
        boolean exist = this.raffleRepository.existsById(id);
        if(!exist) {
            statusMessage.put("Error: ", true);
            statusMessage.put("message", "Raffle does not exists");
            return new ResponseEntity<>(
                    statusMessage,
                    HttpStatus.CONFLICT
            );
        }
        raffleRepository.deleteById(id);
        statusMessage.put("message", "Raffle successfully deleted");
        return new ResponseEntity<>(
                statusMessage,
                HttpStatus.ACCEPTED
        );
    }
}
