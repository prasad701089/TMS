package com.TollManagementSystem.TMS.repository;

import com.TollManagementSystem.TMS.entity.TollTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TollTransactionRepository  extends JpaRepository<TollTransaction, Long> {

}
