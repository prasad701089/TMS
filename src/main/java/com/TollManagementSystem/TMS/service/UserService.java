
package com.TollManagementSystem.TMS.service;

import com.TollManagementSystem.TMS.entity.User;
import com.TollManagementSystem.TMS.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    public User updateUser(Long id, User user) {
        User existing = userRepo.findById(id).orElse(null);
        if (existing == null) return null;
        // Check if username is being changed to one that already exists (and is not this user)
        User userWithSameUsername = userRepo.findByUsername(user.getUsername());
        if (userWithSameUsername != null && !userWithSameUsername.getId().equals(id)) {
            return null;
        }
        existing.setUsername(user.getUsername());
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existing.setPassword(user.getPassword());
        }
        existing.setRole(user.getRole());
        return userRepo.save(existing);
    }

    public boolean deleteUserById(Long id) {
        if (userRepo.existsById(id)) {
            userRepo.deleteById(id);
            return true;
        }
        return false;
    }

    public java.util.List<User> getAllUsers() {
        return userRepo.findAll();
    }




        private final UserRepository userRepo;

        public UserService(UserRepository userRepo) {
            this.userRepo = userRepo;
        }

        public User register(User user) {
            // Check if username already exists
            if (userRepo.findByUsername(user.getUsername()) != null) {
                return null;
            }
            return userRepo.save(user);
        }

        public User login(String username, String password) {
            User u = userRepo.findByUsername(username);
            if (u != null && u.getPassword().equals(password)) {
                return u;
            }
            return null;
        }


}
