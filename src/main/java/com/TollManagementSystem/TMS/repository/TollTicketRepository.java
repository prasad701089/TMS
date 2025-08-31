package com.TollManagementSystem.TMS.repository;

import com.TollManagementSystem.TMS.entity.TollTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TollTicketRepository extends JpaRepository<TollTicket, Long> {
}
