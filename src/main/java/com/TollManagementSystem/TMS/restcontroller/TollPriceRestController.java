package com.TollManagementSystem.TMS.restcontroller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.TollManagementSystem.TMS.service.VehiclePriceService;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/toll")
public class TollPriceRestController {
    private final VehiclePriceService priceService;

    @Autowired
    public TollPriceRestController(VehiclePriceService priceService) {
        this.priceService = priceService;
    }

    @GetMapping("/prices1")
    public Map<String, Integer> getTollPrices() {
        return priceService.getAllPrices();
    }

    // New endpoint for price by vehicle type and plan
    @GetMapping("/price")
    public Map<String, Object> getTollPriceByTypeAndPlan(@RequestParam String vehicleType, @RequestParam String plan) {
        Map<String, Integer> allPrices = priceService.getAllPrices();
        Integer basePrice = allPrices.getOrDefault(vehicleType, 0);
        int price = basePrice;
        if (plan.equalsIgnoreCase("Return")) price = basePrice * 2;
        else if (plan.equalsIgnoreCase("Monthly")) price = basePrice * 30;
        Map<String, Object> result = new HashMap<>();
        result.put("vehicleType", vehicleType);
        result.put("plan", plan);
        result.put("price", price);
        return result;
    }
}
