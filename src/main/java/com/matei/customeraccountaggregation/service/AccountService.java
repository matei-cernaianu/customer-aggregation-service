package com.matei.customeraccountaggregation.service;

import com.matei.customeraccountaggregation.dto.AccountDTO;
import com.matei.customeraccountaggregation.entity.Account;
import com.matei.customeraccountaggregation.mapper.AccountsMapper;
import com.matei.customeraccountaggregation.repository.AccountRepository;
import com.matei.customeraccountaggregation.service.external.ExternalAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.matei.customeraccountaggregation.util.ValidationUtils.areAccountsUpdated;

@Service
public class AccountService {

    @Autowired
    private AccountsMapper accountsMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ExternalAccountService externalAccountService;

    public List<AccountDTO> getAccountsDetails(String username) {
        final List<Account> accounts = accountRepository.getAccountsByName("Account-" + username);

        if (areAccountsUpdated(accounts)) {
            return accountsMapper.mapToDTO(accounts);
        }

        final List<AccountDTO> newAccounts = externalAccountService.getNewAccounts(username);
        accountRepository.saveAll(accountsMapper.mapToEntity(newAccounts));
        return newAccounts;
    }
}
