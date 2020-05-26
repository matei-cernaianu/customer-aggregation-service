package com.matei.customeraccountaggregation.controller;

import com.matei.customeraccountaggregation.dto.TransactionDTO;
import com.matei.customeraccountaggregation.service.TransactionService;
import com.matei.customeraccountaggregation.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/transactions")
@Slf4j
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping
    public List<TransactionDTO> getTransactions(@RequestHeader(name = "username") String username,
                                                @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
        log.info("Transactions requested for user {}", username);
        ValidationUtils.validateUsername(username);
        return transactionService.getAccountsTransactions(username, page, pageSize);
    }
}
