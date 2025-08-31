package com.TollManagementSystem.TMS.controller;

import com.TollManagementSystem.TMS.repository.UserRepository;
import com.TollManagementSystem.TMS.repository.TollTicketRepository;
import com.TollManagementSystem.TMS.entity.TollTicket;
import com.TollManagementSystem.TMS.repository.TollPriceRepository;
import com.TollManagementSystem.TMS.entity.TollPrice;
import com.TollManagementSystem.TMS.entity.User;
import com.TollManagementSystem.TMS.service.UserService;
import java.util.List;

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
        }
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

    private final UserService userService;
    private final UserRepository userRepository;
    private final TollTicketRepository tollTicketRepository;
    private final TollPriceRepository tollPriceRepository;

    public UserController(UserService userService, UserRepository userRepository, TollTicketRepository tollTicketRepository, TollPriceRepository tollPriceRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.tollTicketRepository = tollTicketRepository;
        this.tollPriceRepository = tollPriceRepository;
    }
    @GetMapping("/admin/dashboard")
    public String adminDashboard(Model model) {
        int staffCount = userRepository.countByRole("STAFF");
        int userCount = userRepository.countByRole("USER");
        long tollCount = tollTicketRepository.count();
        model.addAttribute("staffCount", staffCount);
        model.addAttribute("userCount", userCount);
        model.addAttribute("tollCount", tollCount);

        // Fetch recent toll transactions (latest 5)
        List<TollTicket> recentTransactions = tollTicketRepository.findTop5ByOrderByIdDesc();
        // Attach price to each transaction (if not already set)
        for (TollTicket tx : recentTransactions) {
            if (tx.getAmount() == null) {
                TollPrice price = tollPriceRepository.findByVehicleTypeAndPlan(tx.getVehicleType(), tx.getPlan()).orElse(null);
                if (price != null) {
                    tx.setAmount(price.getPrice());
                } else {
                    tx.setAmount(0.0);
                }
            }
        }
        model.addAttribute("recentTransactions", recentTransactions);
        return "admin_dashboard";
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
