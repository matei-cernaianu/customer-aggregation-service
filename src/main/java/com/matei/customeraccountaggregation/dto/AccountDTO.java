package com.matei.customeraccountaggregation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matei.customeraccountaggregation.enums.AccountStatus;
import com.matei.customeraccountaggregation.enums.AccountType;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDTO {
    private UUID id;
    private Instant update;
    private String name;
    private String product;
    private AccountStatus status;
    private AccountType type;
    private BigDecimal balance;
}
