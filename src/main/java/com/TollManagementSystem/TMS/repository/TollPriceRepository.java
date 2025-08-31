
package com.TollManagementSystem.TMS.repository;

import com.TollManagementSystem.TMS.entity.TollPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TollPriceRepository extends JpaRepository<TollPrice, Long> {
	Optional<TollPrice> findByVehicleTypeAndPlan(String vehicleType, String plan);
}
