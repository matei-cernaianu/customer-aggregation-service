package com.matei.customeraccountaggregation.unit.controller;

import com.matei.customeraccountaggregation.controller.AccountController;
import com.matei.customeraccountaggregation.dto.AccountDTO;
import com.matei.customeraccountaggregation.exception.ExceptionController;
import com.matei.customeraccountaggregation.service.AccountService;
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
public class AccountControllerTest {

    private static final String USERNAME_OK = "matei";
    private static final String USERNAME_FAIL = "matei.@";

    private MockMvc mvc;
    private AccountDTO accountDTO;

    @InjectMocks
    private AccountController accountController;

    @Mock
    private AccountService accountService;

    @Before
    public void init() {
        accountDTO = DtoUtil.generateAccountDto();
        mvc = MockMvcBuilders
                .standaloneSetup(accountController)
                .setControllerAdvice(ExceptionController.class)
                .build();
    }

    @Test
    public void givenAccounts_whenGet_thenSuccess() throws Exception {
        when(accountService.getAccountsDetails(USERNAME_OK)).thenReturn(Collections.singletonList(accountDTO));

        mvc.perform(get("/v1/accounts")
                .header("username", USERNAME_OK))
                .andExpect(status().isOk());

        verify(accountService, times(1)).getAccountsDetails(USERNAME_OK);
    }

    @Test
    public void givenRestCallWithoutHeader_whenGet_thenFail() throws Exception {

        mvc.perform(get("/v1/accounts"))
                .andExpect(status().isBadRequest());

        verify(accountService, never()).getAccountsDetails(USERNAME_OK);
    }

    @Test
    public void givenWrongUsername_whenGet_thenFail() throws Exception {

        mvc.perform(get("/v1/accounts")
                .header("username", USERNAME_FAIL))
                .andExpect(status().isBadRequest());

        verify(accountService, never()).getAccountsDetails(USERNAME_FAIL);
    }
}
