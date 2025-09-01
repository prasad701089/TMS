package com.TollManagementSystem.TMS.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TollTicket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String branchNo;
    private String state;
    private String district;
    private String nhNumber;
    private String tollName;
    private String tollKm;
    private String section;
    private String vehicleType;
    private String plan;
    private String vehicleNumber;

    private Double amount; // price for this transaction

    @Column(nullable = false, updatable = false)
    private java.time.LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = java.time.LocalDateTime.now();
    }
}
