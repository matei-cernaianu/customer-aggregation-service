package com.matei.customeraccountaggregation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Creditor {
    private String maskedPan;
    private String name;
}
