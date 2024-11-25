package com.example.rafflesystemapi.Service;

import com.example.rafflesystemapi.Repository.TicketRepository;
import com.example.rafflesystemapi.ViewModel.Ticket;
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

    public ResponseEntity<Object> newTicket(Ticket ticketVM) {
        statusMessage = new HashMap<>();
        Optional<Ticket> res = ticketRepository.findTicketByNumber(ticketVM.getNumber());
        statusMessage.put("name", ticketVM.getNumber());

        if(res.isPresent() && ticketVM.getId() == null) {
            statusMessage.put("Error: ", true);
            statusMessage.put("message", "Ticket already exists");
            return new ResponseEntity<>(
                    statusMessage,
                    HttpStatus.CONFLICT
            );
        }
        statusMessage.put("message", "Ticket created successfully");
        if(ticketVM.getId() > 0) {
            statusMessage.put("message", "Ticket successfully updated");
        }
        ticketRepository.save(ticketVM);
        statusMessage.put("Data: ", ticketVM);

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












