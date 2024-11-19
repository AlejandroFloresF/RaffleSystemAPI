package com.example.rafflesystemapi.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/tickets")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public List<Ticket> getAllRaffles() {
        return ticketService.getAllTickets();
    }

    @PostMapping
    public ResponseEntity<Object> addTicket(@RequestBody Ticket ticket) {
        return this.ticketService.newTicket(ticket);
    }

    @PutMapping
    public ResponseEntity<Object> updateTicket(@RequestBody Ticket ticket) {
        return this.ticketService.newTicket(ticket);
    }

    @DeleteMapping(path = "{ticketId}")
    public ResponseEntity<Object> deleteTicket(@PathVariable("ticketId") Integer id) {
        return this.ticketService.deleteTicket(id);
    }




}
