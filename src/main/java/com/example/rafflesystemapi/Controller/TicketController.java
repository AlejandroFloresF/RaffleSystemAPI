package com.example.rafflesystemapi.Controller;
import com.example.rafflesystemapi.ViewModel.Ticket;
import com.example.rafflesystemapi.Service.TicketService;
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
    public List<Ticket> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @PostMapping
    public ResponseEntity<Object> addTicket(@RequestBody Ticket ticketVM) {
        return this.ticketService.newTicket(ticketVM);
    }

    @PutMapping
    public ResponseEntity<Object> updateTicket(@RequestBody Ticket ticketVM) {
        return this.ticketService.newTicket(ticketVM);
    }

    @DeleteMapping(path = "{ticketId}")
    public ResponseEntity<Object> deleteTicket(@PathVariable("ticketId") Integer id) {
        return this.ticketService.deleteTicket(id);
    }




}
