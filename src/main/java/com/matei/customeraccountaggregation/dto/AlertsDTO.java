package com.matei.customeraccountaggregation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AlertsDTO {
    List<AlertDTO> alerts;
}
