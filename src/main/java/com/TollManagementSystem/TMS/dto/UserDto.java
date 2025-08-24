package com.TollManagementSystem.TMS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;

    private String username;
    private String email;
    private String phone;
    private String password; // (hash in real app)
    private String role; // ADMIN, OPERATOR
}
