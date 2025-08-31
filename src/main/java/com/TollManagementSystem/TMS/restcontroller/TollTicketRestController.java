package com.TollManagementSystem.TMS.restcontroller;

import com.TollManagementSystem.TMS.entity.TollTicket;
import com.TollManagementSystem.TMS.repository.TollTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/toll")
public class TollTicketRestController {
    private final TollTicketRepository ticketRepository;

    @Autowired
    public TollTicketRestController(TollTicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @PostMapping("/ticket")
    public ResponseEntity<TollTicket> bookTollTicket(@RequestBody TollTicket ticket) {

        TollTicket ticket1=ticketRepository.save(ticket);
        return ResponseEntity.ok(ticket1);
    }
}
