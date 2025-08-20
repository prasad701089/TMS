package com.TollManagementSystem.TMS.restcontroller;

import com.TollManagementSystem.TMS.entity.User;
import com.TollManagementSystem.TMS.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserRestController {

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        User updated = userService.updateUser(id, user);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"User not found or username exists\"}");
        }
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            return ResponseEntity.ok("{\"message\": \"User deleted\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"error\": \"User not found\"}");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        User registered = userService.register(user);
        if (registered == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("{\"error\": \"Username already exists\"}");
        }
        return ResponseEntity.ok("{\"message\": \"Registration successful\"}");
    }

    @PostMapping("/login")
public ResponseEntity<?> loginUser(@RequestBody User user, HttpServletRequest request) {
    User loggedIn = userService.login(user.getUsername(), user.getPassword());
    if (loggedIn == null) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("{\"error\": \"Invalid username or password\"}");
    }
    // Set session attributes
    request.getSession().setAttribute("username", loggedIn.getUsername());
    request.getSession().setAttribute("role", loggedIn.getRole());
    return ResponseEntity.ok(loggedIn); // this will be JSON
}
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        request.getSession().invalidate(); // destroy session
        return ResponseEntity.ok("{\"message\": \"Logged out\"}");
    }

}
