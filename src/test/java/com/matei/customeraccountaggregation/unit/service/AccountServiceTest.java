package com.matei.customeraccountaggregation.unit.service;

import com.matei.customeraccountaggregation.dto.AccountDTO;
import com.matei.customeraccountaggregation.entity.Account;
import com.matei.customeraccountaggregation.mapper.AccountsMapper;
import com.matei.customeraccountaggregation.mapper.AccountsMapperImpl;
import com.matei.customeraccountaggregation.repository.AccountRepository;
import com.matei.customeraccountaggregation.service.AccountService;
import com.matei.customeraccountaggregation.service.external.ExternalAccountService;
import com.matei.customeraccountaggregation.unit.util.DtoUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

    private static final String USERNAME_OK = "matei";

    private AccountDTO accountDTO;
    private AccountsMapper accountsMapper;

    @Mock
    private AccountsMapperImpl mockAccountsMapper;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private ExternalAccountService externalAccountService;

    @InjectMocks
    private AccountService accountService;

    @Before
    public void init() {
        accountDTO = DtoUtil.generateAccountDto();
        accountsMapper = new AccountsMapperImpl();
    }

    @Test
    public void givenOutdatedAccount_whenGetAccounts_thenSuccess() {

        final List<Account> accountEntity = Collections.singletonList(accountsMapper.mapFromDTO(accountDTO));
        accountEntity.get(0).setLastUpdatedTimestamp(Instant.now().minus(2, ChronoUnit.DAYS));

        when(accountRepository.getAccountsByName("Account-" + USERNAME_OK))
                .thenReturn(accountEntity);
        when(accountRepository.saveAll(Collections.singletonList(accountsMapper.mapFromDTO(accountDTO))))
                .thenReturn(accountEntity);
        when(externalAccountService.getNewAccounts(USERNAME_OK))
                .thenReturn(Collections.singletonList(accountDTO));

        List<AccountDTO> accounts = accountService.getAccountsDetails(USERNAME_OK);

        assertEquals(1, accounts.size());
        assertEquals(accountDTO, accounts.get(0));
        verify(accountRepository, times(1)).getAccountsByName(any());
        verify(accountRepository, times(1)).saveAll(any());
        verify(externalAccountService, times(1)).getNewAccounts(any());
    }

    @Test
    public void givenUpdatedAccount_whenGetAccounts_thenSuccess() {

        final List<Account> accountEntity = Collections.singletonList(accountsMapper.mapFromDTO(accountDTO));
        accountEntity.get(0).setLastUpdatedTimestamp(Instant.now());

        when(accountRepository.getAccountsByName("Account-" + USERNAME_OK))
                .thenReturn(accountEntity);
        when(mockAccountsMapper.mapToDTO(accountEntity))
                .thenReturn(Collections.singletonList(accountDTO));

        List<AccountDTO> accounts = accountService.getAccountsDetails(USERNAME_OK);

        assertEquals(1, accounts.size());
        assertEquals(accountDTO, accounts.get(0));
        verify(accountRepository, times(1)).getAccountsByName(any());
        verify(mockAccountsMapper, times(1)).mapToDTO(anyList());
        verify(accountRepository, never()).saveAll(any());
        verify(externalAccountService, never()).getNewAccounts(any());
    }
}
