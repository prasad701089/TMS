
package com.TollManagementSystem.TMS.restcontroller;

import com.TollManagementSystem.TMS.entity.TollTicket;
import com.TollManagementSystem.TMS.repository.TollTicketRepository;

import java.util.List;

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

       // TollTicket ticket1=ticketRepository.save(ticket);
        // Fetch price from TollPrice table
       // TollPrice price = tollPriceRepository.findByVehicleTypeAndPlan(ticket.getVehicleType(), ticket.getPlan()).orElse(null);
       // if (price != null) {
         //   ticket.setAmount(price.getPrice());
        //} else {
            // Fallback: set amount using case logic
            String vt = ticket.getVehicleType();
            String plan = ticket.getPlan();
            double amt = 0.0;
            if ("Car".equalsIgnoreCase(vt)) {
                if ("single".equalsIgnoreCase(plan)) amt = 50;
                else if ("return".equalsIgnoreCase(plan)) amt = 100;
                else if ("monthly".equalsIgnoreCase(plan)) amt = 1500;
            } else if ("Truck".equalsIgnoreCase(vt)) {
                if ("single".equalsIgnoreCase(plan)) amt = 100;
                else if ("return".equalsIgnoreCase(plan)) amt = 200;
                else if ("monthly".equalsIgnoreCase(plan)) amt = 3000;
            } else if ("Bus".equalsIgnoreCase(vt)) {
                if ("single".equalsIgnoreCase(plan)) amt = 150;
                else if ("return".equalsIgnoreCase(plan)) amt = 300;
                else if ("monthly".equalsIgnoreCase(plan)) amt = 4500;
            } else if ("Jeep".equalsIgnoreCase(vt)) {
                if ("single".equalsIgnoreCase(plan)) amt = 130;
                else if ("return".equalsIgnoreCase(plan)) amt = 260;
                else if ("monthly".equalsIgnoreCase(plan)) amt = 3900;
            } else if ("Three wheeler".equalsIgnoreCase(vt)) {
                if ("single".equalsIgnoreCase(plan)) amt = 90;
                else if ("return".equalsIgnoreCase(plan)) amt = 180;
                else if ("monthly".equalsIgnoreCase(plan)) amt = 2700;
            }
            ticket.setAmount(amt);
        
        TollTicket ticket1 = ticketRepository.save(ticket);
        return ResponseEntity.ok(ticket1);
    }
    @GetMapping("/reports")
    public List<TollTicket> getAllTickets() {
        return ticketRepository.findAll();
    }
}
