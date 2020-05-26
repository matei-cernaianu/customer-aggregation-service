package com.matei.customeraccountaggregation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matei.customeraccountaggregation.enums.CurrencyCode;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRate {
    private CurrencyCode currencyFrom;
    private CurrencyCode currencyTo;
    private BigDecimal rate;
}
