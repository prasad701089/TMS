package com.TollManagementSystem.TMS.repository;

import com.TollManagementSystem.TMS.entity.TollTicket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TollTicketRepository extends JpaRepository<TollTicket, Long> {
	List<TollTicket> findTop5ByOrderByIdDesc();

	@Query("SELECT COALESCE(SUM(t.amount), 0) FROM TollTicket t WHERE t.createdAt >= :start AND t.createdAt <= :end")
	Double sumAmountByDate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);

	@Query("SELECT COUNT(t) FROM TollTicket t WHERE t.createdAt >= :start AND t.createdAt <= :end")
	int countByDate(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
	
}
