package com.matei.customeraccountaggregation.controller;

import com.matei.customeraccountaggregation.dto.AccountDTO;
import com.matei.customeraccountaggregation.service.AccountService;
import com.matei.customeraccountaggregation.util.ValidationUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/accounts")
@Slf4j
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<AccountDTO> getAccount(@RequestHeader(name = "username") String username) {
        log.info("Accounts requested for user {}", username);
        ValidationUtils.validateUsername(username);
        return accountService.getAccountsDetails(username);
    }
}
