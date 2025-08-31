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
    public String myProfile(Model model, @SessionAttribute(name = "username", required = false) String username) {
        if (username != null) {
            User user = userService.getUserByUsername(username);
            model.addAttribute("user", user);
        }
        return "my_profile";
    }
    @GetMapping("/user/dashboard")
    public String userDashboard(Model model, @SessionAttribute(name = "username", required = false) String username) {
        if (username != null) {
            User user = userService.getUserByUsername(username);
            model.addAttribute("user", user);

            // Fetch total tolls paid and total amount paid for this user
            int totalTollsPaid = tollTicketRepository.countByUsername(username);
            double totalAmountPaid = tollTicketRepository.sumAmountByUsername(username);
            model.addAttribute("totalTollsPaid", totalTollsPaid);
            model.addAttribute("totalAmountPaid", totalAmountPaid);
        }
        return "user_dashboard";
    }
    private final TollTicketRepository tollTicketRepository;

    public UserController(UserService userService, TollTicketRepository tollTicketRepository) {
        this.userService = userService;
        this.tollTicketRepository = tollTicketRepository;
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
