package com.matei.customeraccountaggregation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matei.customeraccountaggregation.enums.CurrencyCode;
import com.matei.customeraccountaggregation.enums.TransactionStatus;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class TransactionDTO {
    private UUID id;
    private UUID accountId;
    private ExchangeRate exchangeRate;
    private OriginalAmount originalAmount;
    private Creditor creditor;
    private Debtor debtor;
    private TransactionStatus status;
    private CurrencyCode currency;
    private BigDecimal amount;
    private Instant update;
    private String description;
}
