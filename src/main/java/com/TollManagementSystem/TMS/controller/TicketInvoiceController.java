package com.TollManagementSystem.TMS.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TicketInvoiceController {
    @GetMapping("/ticket_invoice.html")
    public String ticketInvoicePage() {
        return "ticket_invoice";
    }
    @GetMapping("/download_invoice.html")
    public String downloadInvoicePage() {
        return "download_invoice";
    }
}
