package com.example.rafflesystemapi.Raffle;

import com.example.rafflesystemapi.Ticket.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.support.NullValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class RaffleService {

    HashMap<String, Object> statusMessage;

    @Autowired
    private final RaffleRepository raffleRepository;
    private final TicketRepository ticketRepository;

    @Autowired
    public RaffleService(RaffleRepository raffleRepository, TicketRepository ticketRepository) {
        this.raffleRepository = raffleRepository;
        this.ticketRepository = ticketRepository;
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

    public List<Integer> getAvailableTickets(Long raffleId) {
        statusMessage = new HashMap<>();
        Raffle raffle = raffleRepository.findById(raffleId)
                .orElseThrow(() -> new RuntimeException("Raffle not found"));
        if(raffle.getId() == null) {
            statusMessage.put("Error: ", true);
            statusMessage.put("message", "Raffle does not exists");
        }
        List<Integer> purchasedTickets = ticketRepository.findPurchasedTicketsByRaffleId(raffleId);

        List<Integer> availableTickets = new ArrayList<>();
        for (int i = 1; i <= raffle.getMaxTickets(); i++) {
            if (!purchasedTickets.contains(i)) {
                availableTickets.add(i);
            }
        }
        if(!availableTickets.isEmpty()) {
            statusMessage.put("Data:", availableTickets);
        }
        return availableTickets;
    }


    public void saveBackgroundImage(Long id, MultipartFile file) throws IOException {
        Raffle raffle = raffleRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Raffle not found with ID: " + id));
        raffle.setBackgroundImage(file.getBytes());
        raffleRepository.save(raffle);
    }


}
