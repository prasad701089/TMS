package com.TollManagementSystem.TMS.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "toll_price")
public class TollPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String vehicleType;

    @Column(nullable = false)
    private String plan; // single, return, monthly

    @Column(nullable = false)
    private Double price;

    // Constructors
    public TollPrice() {}

    public TollPrice(String vehicleType, String plan, Double price) {
        this.vehicleType = vehicleType;
        this.plan = plan;
        this.price = price;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
