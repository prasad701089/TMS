package com.TollManagementSystem.TMS.controller;

import com.TollManagementSystem.TMS.entity.User;
import com.TollManagementSystem.TMS.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {
    @GetMapping("/book-toll-ticket")
    public String bookTollTicketUser() {
        return "book_toll_ticket_user";
    }
    @GetMapping("/my-profile")
    public String myProfile() {
        return "my_profile";
    }
    @GetMapping("/user/dashboard")
    public String userDashboard() {
        return "user_dashboard";
    }

    @GetMapping("/staff/dashboard")
    public String staffDashboard() {
        return "staff_dashboard";
    }
    @GetMapping("/manage-staffs")
    public String manageStaffs() {
        return "manage_staffs";
    }

    @GetMapping("/manage-users")
    public String manageUsers() {
        return "manage_users";
    }
    @GetMapping("/ticket")
    public String ticketpage()
    {
        return "book_toll_ticket";
    }

    @GetMapping("/toll-reports")
    public String tollReports() {
        return "toll_reports";
    }
    @GetMapping("/admin/dashboard")
    public String adminDashboard() {
        return "admin_dashboard";
    }

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/home")
    public String homePage() {
        return "home"; // renders home.html from templates/
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // renders login.html
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register"; // renders register.html
    }
    @GetMapping("/toll")
    public String toll()
    {
        return "toll";
    }


}
