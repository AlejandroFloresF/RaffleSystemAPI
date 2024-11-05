package com.example.rafflesystemapi.Raffle;

import org.springframework.beans.factory.annotation.Autowired;
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
    public void addRaffle(@RequestBody Raffle raffle) {
        this.raffleService.newRaffle(raffle);
    }

}
