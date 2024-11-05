package com.example.rafflesystemapi.Raffle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/raffles")
public class RaffleController {

    private final RaffleService raffleService;

    @Autowired
    public RaffleController(RaffleService raffleService) {
        this.raffleService = raffleService;
    }


    @GetMapping
    public List<Raffle> getAllRaffles() {
        return raffleService.getAllRaffles();
    }

    @PostMapping
    public ResponseEntity<Object> addRaffle(@RequestBody Raffle raffle) {
        return this.raffleService.newRaffle(raffle);
    }

    @PutMapping
    public ResponseEntity<Object> updateRaffle(@RequestBody Raffle raffle) {
        return this.raffleService.newRaffle(raffle);
    }

    @DeleteMapping(path = "{raffleId}")
    public ResponseEntity<Object> deleteRaffle(@PathVariable("raffleId") Long id) {
        return this.raffleService.deleteRaffle(id);
    }
}
