package com.TollManagementSystem.TMS.repository;

import com.TollManagementSystem.TMS.entity.TollTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TollTicketRepository extends JpaRepository<TollTicket, Long> {
	@Query("SELECT COUNT(t) FROM TollTicket t WHERE t.vehicleNumber = :username")
	int countByUsername(@Param("username") String username);

	@Query("SELECT COALESCE(SUM(t.price), 0) FROM TollTicket t WHERE t.vehicleNumber = :username")
	double sumAmountByUsername(@Param("username") String username);
}
