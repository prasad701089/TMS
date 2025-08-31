package com.TollManagementSystem.TMS.controller;

import com.TollManagementSystem.TMS.entity.TollTransaction;
import com.TollManagementSystem.TMS.entity.Vehicle;
import com.TollManagementSystem.TMS.service.TollService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/toll")
public class TollController {





        private final TollService tollService;

        public TollController(TollService tollService) {
            this.tollService = tollService;
        }

        @PostMapping("/vehicle")
        public Vehicle registerVehicle(@RequestBody Vehicle v) {
            return tollService.registerVehicle(v);
        }

        @PostMapping("/pay/{vehicleId}")
        public TollTransaction payToll(@PathVariable Long vehicleId, @RequestParam double amount) {
            return tollService.recordToll(vehicleId, amount);
        }

        @GetMapping("/transactions")
        public List<TollTransaction> getAllTransactions() {
            return tollService.getAllTransactions();
        }
        
    }


