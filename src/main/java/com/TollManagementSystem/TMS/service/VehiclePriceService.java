package com.TollManagementSystem.TMS.service;

import com.TollManagementSystem.TMS.entity.TollPrice;
import com.TollManagementSystem.TMS.repository.TollPriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.List;

@Service
public class VehiclePriceService {
	private final TollPriceRepository tollPriceRepository;

	@Autowired
	public VehiclePriceService(TollPriceRepository tollPriceRepository) {
		this.tollPriceRepository = tollPriceRepository;
	}

	// Returns a map of vehicleType to base price (for /prices1 endpoint)
	public Map<String, Integer> getAllPrices() {
		List<TollPrice> prices = tollPriceRepository.findAll();
		Map<String, Integer> priceMap = new HashMap<>();
		for (TollPrice price : prices) {
			// Only add base price for each vehicle type (plan = 'Single')
			if ("Single".equalsIgnoreCase(price.getPlan())) {
				priceMap.put(price.getVehicleType(), price.getPrice().intValue());
			}
		}
		return priceMap;
	}

	// Returns price for a specific vehicle type and plan
	public Double getPriceByVehicleTypeAndPlan(String vehicleType, String plan) {
		Optional<TollPrice> priceOpt = tollPriceRepository.findByVehicleTypeAndPlan(vehicleType, plan);
		return priceOpt.map(TollPrice::getPrice).orElse(0.0);
	}
}
