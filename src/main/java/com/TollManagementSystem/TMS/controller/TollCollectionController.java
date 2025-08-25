package com.TollManagementSystem.TMS.controller;

import com.TollManagementSystem.TMS.entity.TollTransaction;
import com.TollManagementSystem.TMS.entity.Vehicle;
import com.TollManagementSystem.TMS.service.TollService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TollCollectionController {
    private final TollService tollService;

    public TollCollectionController(TollService tollService) {
        this.tollService = tollService;
    }
    @GetMapping("/toll/collect")
    public String showTollCollectionPage() {
        return "toll_collection";
    }

    // Optionally, you can add a POST endpoint for form submission if needed for non-JS fallback
}
