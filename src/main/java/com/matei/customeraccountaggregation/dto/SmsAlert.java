package com.matei.customeraccountaggregation.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SmsAlert implements Serializable {
    private String name;
    private String phoneNumber;
    private Long sum;
}
