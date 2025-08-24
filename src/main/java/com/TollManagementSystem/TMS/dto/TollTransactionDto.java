package com.TollManagementSystem.TMS.dto;

import com.TollManagementSystem.TMS.entity.Vehicle;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TollTransactionDto {
    private Long id;

    @ManyToOne
    private Vehicle vehicle;

    private double tollAmount;
    private LocalDateTime timestamp;
}
