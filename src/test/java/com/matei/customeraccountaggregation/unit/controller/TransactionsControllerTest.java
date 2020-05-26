package com.matei.customeraccountaggregation.unit.controller;

import com.matei.customeraccountaggregation.controller.TransactionController;
import com.matei.customeraccountaggregation.dto.TransactionDTO;
import com.matei.customeraccountaggregation.exception.ExceptionController;
import com.matei.customeraccountaggregation.service.TransactionService;
import com.matei.customeraccountaggregation.unit.util.DtoUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class TransactionsControllerTest {

    private static final String USERNAME_OK = "matei";
    private static final String USERNAME_FAIL = "matei.@";

    private MockMvc mvc;
    private TransactionDTO transactionDTO;

    @InjectMocks
    private TransactionController transactionController;

    @Mock
    private TransactionService transactionService;

    @Before
    public void init() {
        transactionDTO = DtoUtil.generateTransactionDTO();
        mvc = MockMvcBuilders
                .standaloneSetup(transactionController)
                .setControllerAdvice(ExceptionController.class)
                .build();
    }

    @Test
    public void givenTransactions_whenGet_thenSuccess() throws Exception {
        when(transactionService.getAccountsTransactions(USERNAME_OK, 0, 1))
                .thenReturn(Collections.singletonList(transactionDTO));

        mvc.perform(get("/v1/transactions")
                .header("username", USERNAME_OK)
                .param("page", String.valueOf(0))
                .param("pageSize", String.valueOf(1)))
                .andExpect(status().isOk());

        verify(transactionService, times(1)).getAccountsTransactions(USERNAME_OK, 0, 1);
    }

    @Test
    public void givenRestCallWithoutHeader_whenGet_thenFail() throws Exception {

        mvc.perform(get("/v1/transactions"))
                .andExpect(status().isBadRequest());

        verify(transactionService, never()).getAccountsTransactions(USERNAME_OK, 0, 10);
    }

    @Test
    public void givenWrongUsername_whenGet_thenFail() throws Exception {

        mvc.perform(get("/v1/transactions")
                .header("username", USERNAME_FAIL))
                .andExpect(status().isBadRequest());

        verify(transactionService, never()).getAccountsTransactions(USERNAME_FAIL, 0,10);
    }
}
