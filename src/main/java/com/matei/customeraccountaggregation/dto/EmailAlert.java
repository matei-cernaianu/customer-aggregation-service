package com.matei.customeraccountaggregation.dto;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailAlert implements Serializable {
    private String name;
    private String email;
    private Long sum;
}
