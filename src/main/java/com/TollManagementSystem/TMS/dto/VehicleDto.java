package com.TollManagementSystem.TMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleDto {
    private Long id;

    private String plateNumber;
    private String type; // Car, Truck, Bus
}
