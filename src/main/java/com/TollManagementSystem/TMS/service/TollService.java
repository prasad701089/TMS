package com.TollManagementSystem.TMS.service;

import com.TollManagementSystem.TMS.entity.TollTransaction;
import com.TollManagementSystem.TMS.entity.Vehicle;
import com.TollManagementSystem.TMS.repository.TollTransactionRepository;
import com.TollManagementSystem.TMS.repository.VehicleRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TollService {




        private final VehicleRepository vehicleRepo;
        private final TollTransactionRepository transactionRepo;

        public TollService(VehicleRepository vehicleRepo, TollTransactionRepository transactionRepo) {
            this.vehicleRepo = vehicleRepo;
            this.transactionRepo = transactionRepo;
        }

        public Vehicle registerVehicle(Vehicle v) {
            return vehicleRepo.save(v);
        }

        public TollTransaction recordToll(Long vehicleId, double amount) {
            Vehicle v = vehicleRepo.findById(vehicleId).orElseThrow();
            TollTransaction tx = new TollTransaction(null, v, amount, LocalDateTime.now());
            return transactionRepo.save(tx);
        }

        public List<TollTransaction> getAllTransactions() {
            return transactionRepo.findAll();
        }


}
