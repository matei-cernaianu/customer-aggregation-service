package com.matei.customeraccountaggregation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Debtor {
    private String maskedPan;
    private String name;
}
