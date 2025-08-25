package com.TollManagementSystem.TMS.restcontroller;

import com.TollManagementSystem.TMS.dto.TollTransactionDto;
import com.TollManagementSystem.TMS.entity.TollTransaction;
import com.TollManagementSystem.TMS.mapper.TollTransactionMapper;
import com.TollManagementSystem.TMS.service.TollService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/toll-reports")
public class TollReportRestController {
    private final TollService tollService;

    public TollReportRestController(TollService tollService) {
        this.tollService = tollService;
    }

    @GetMapping
    public List<TollTransactionDto> getAllTollReports() {
        List<TollTransaction> transactions = tollService.getAllTransactions();
        return transactions.stream()
                .map(TollTransactionMapper::toDto)
                .collect(Collectors.toList());
    }
}
