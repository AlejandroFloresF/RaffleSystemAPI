package com.example.rafflesystemapi.Ticket;

import com.example.rafflesystemapi.Raffle.RaffleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    HashMap<String, Object> statusMessage;

    private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public List<Ticket> getAllTickets() {
        return this.ticketRepository.findAll();
    }

    public ResponseEntity<Object> newTicket(Ticket ticket) {
        statusMessage = new HashMap<>();
        Optional<Ticket> res = ticketRepository.findTicketByNumber(ticket.getNumber());
        statusMessage.put("name", ticket.getNumber());

        if(res.isPresent() && ticket.getId() == null) {
            statusMessage.put("Error: ", true);
            statusMessage.put("message", "Ticket already exists");
            return new ResponseEntity<>(
                    statusMessage,
                    HttpStatus.CONFLICT
            );
        }
        statusMessage.put("message", "Ticket created successfully");
        if(ticket.getId() > 0) {
            statusMessage.put("message", "Ticket successfully updated");
        }
        ticketRepository.save(ticket);
        statusMessage.put("Data: ", ticket);

        return new ResponseEntity<>(
                statusMessage,
                HttpStatus.CREATED
        );
    }

    public ResponseEntity<Object> deleteTicket(Integer id) {
        statusMessage = new HashMap<>();
        boolean exist = this.ticketRepository.existsById(id);
        if(!exist) {
            statusMessage.put("Error: ", true);
            statusMessage.put("message", "Ticket does not exists");
            return new ResponseEntity<>(
                    statusMessage,
                    HttpStatus.CONFLICT
            );
        }
        ticketRepository.deleteById(id);
        statusMessage.put("message", "Ticket successfully deleted");
        return new ResponseEntity<>(
                statusMessage,
                HttpStatus.ACCEPTED
        );
    }
}












