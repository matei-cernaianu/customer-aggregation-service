package com.matei.customeraccountaggregation.unit.service;

import com.matei.customeraccountaggregation.dto.AccountDTO;
import com.matei.customeraccountaggregation.dto.TransactionDTO;
import com.matei.customeraccountaggregation.entity.Transaction;
import com.matei.customeraccountaggregation.mapper.TransactionsMapper;
import com.matei.customeraccountaggregation.mapper.TransactionsMapperImpl;
import com.matei.customeraccountaggregation.repository.TransactionRepository;
import com.matei.customeraccountaggregation.service.AccountService;
import com.matei.customeraccountaggregation.service.TransactionService;
import com.matei.customeraccountaggregation.service.external.ExternalTransactionService;
import com.matei.customeraccountaggregation.unit.util.DtoUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.PageRequest;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.never;

@RunWith(MockitoJUnitRunner.class)
public class TransactionsServiceTest {

    private static final String USERNAME_OK = "matei";

    private TransactionDTO transactionDTO;
    private AccountDTO accountDTO;
    private TransactionsMapper transactionsMapper;

    @Mock
    private TransactionsMapperImpl mockTransactionsMapper;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private ExternalTransactionService externalTransactionService;

    @Mock
    private AccountService accountService;

    @InjectMocks
    private TransactionService transactionService;

    @Before
    public void init() {
        accountDTO = DtoUtil.generateAccountDto();
        transactionDTO = DtoUtil.generateTransactionDTO();
        transactionsMapper = new TransactionsMapperImpl();
    }

    @Test
    public void givenOutdatedTransaction_whenGetAccounts_thenSuccess() {

        final List<Transaction> transactionsEntity =
                transactionsMapper.maptoEntity(Collections.singletonList(transactionDTO));
        transactionsEntity.get(0).setLastUpdatedTimestamp(Instant.now().minus(2, ChronoUnit.DAYS));

        when(accountService.getAccountsDetails(USERNAME_OK))
                .thenReturn(Collections.singletonList(accountDTO));
        when(transactionRepository.findAllByAccountIdInOrderByUpdateDesc(
                Collections.singletonList(accountDTO.getId()), PageRequest.of(0, 1)))
                .thenReturn(transactionsEntity);
        when(externalTransactionService.getNewTransactions(USERNAME_OK))
                .thenReturn(Collections.singletonList(transactionDTO));

        List<TransactionDTO> transactions = transactionService.getAccountsTransactions(USERNAME_OK, 0,1);

        assertEquals(1, transactions.size());
        assertEquals(transactionDTO, transactions.get(0));
        verify(accountService, times(1)).getAccountsDetails(any());
        verify(transactionRepository, times(1)).findAllByAccountIdInOrderByUpdateDesc(any(), any());
        verify(externalTransactionService, times(1)).getNewTransactions(any());
    }

    @Test
    public void givenUpdatedTransaction_whenGetAccounts_thenSuccess() {

        final List<Transaction> transactionsEntity =
                transactionsMapper.maptoEntity(Collections.singletonList(transactionDTO));
        transactionsEntity.get(0).setLastUpdatedTimestamp(Instant.now());

        when(accountService.getAccountsDetails(USERNAME_OK))
                .thenReturn(Collections.singletonList(accountDTO));
        when(transactionRepository.findAllByAccountIdInOrderByUpdateDesc(
                Collections.singletonList(accountDTO.getId()), PageRequest.of(0, 1)))
                .thenReturn(transactionsEntity);

        when(mockTransactionsMapper.maptoDTO(transactionsEntity))
                .thenReturn(Collections.singletonList(transactionDTO));

        List<TransactionDTO> transactions = transactionService.getAccountsTransactions(USERNAME_OK, 0,1);

        assertEquals(1, transactions.size());
        assertEquals(transactionDTO, transactions.get(0));
        verify(accountService, times(1)).getAccountsDetails(any());
        verify(transactionRepository, times(1)).findAllByAccountIdInOrderByUpdateDesc(any(), any());
        verify(externalTransactionService, never()).getNewTransactions(any());
    }
}
