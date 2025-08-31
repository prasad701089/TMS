package com.TollManagementSystem.TMS.repository;

import com.TollManagementSystem.TMS.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository  extends JpaRepository<User, Long>{
    User findByUsername(String username);

    @Query("SELECT COUNT(u) FROM User u WHERE u.role = :role")
    int countByRole(@Param("role") String role);
}
