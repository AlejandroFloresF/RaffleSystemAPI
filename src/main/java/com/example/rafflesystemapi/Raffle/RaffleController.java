package com.example.rafflesystemapi.Raffle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
