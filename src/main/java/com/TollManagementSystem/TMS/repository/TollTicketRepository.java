package com.TollManagementSystem.TMS.repository;

import com.TollManagementSystem.TMS.entity.TollTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TollTicketRepository extends JpaRepository<TollTicket, Long> {
	List<TollTicket> findTop5ByOrderByIdDesc();
}
