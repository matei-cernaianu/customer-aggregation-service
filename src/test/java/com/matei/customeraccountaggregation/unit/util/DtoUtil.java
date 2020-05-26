package com.matei.customeraccountaggregation.unit.util;

import com.matei.customeraccountaggregation.dto.AccountDTO;
import com.matei.customeraccountaggregation.dto.TransactionDTO;
import com.matei.customeraccountaggregation.enums.AccountStatus;
import com.matei.customeraccountaggregation.enums.AccountType;
import com.matei.customeraccountaggregation.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

public class DtoUtil {

    public static AccountDTO generateAccountDto() {
        return AccountDTO.builder()
                .id(UUID.randomUUID())
                .balance(BigDecimal.valueOf(100999.99965544))
                .name("Name Test")
                .product("Test Product")
                .type(AccountType.CREDIT_CARD)
                .status(AccountStatus.ENABLED)
                .update(Instant.now())
                .build();
    }

    public static TransactionDTO generateTransactionDTO() {
        return TransactionDTO.builder()
                .id(UUID.randomUUID())
                .amount(BigDecimal.valueOf(100999.99965544))
                .accountId(UUID.randomUUID())
                .description("Test description")
                .status(TransactionStatus.BOOKED)
                .update(Instant.now())
                .build();
    }
}
