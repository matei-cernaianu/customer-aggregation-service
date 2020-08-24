package com.matei.customeraccountaggregation.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class TestAlertDTO {
    @NotNull
    private UUID accountId;

    @NotNull
    private Long userId;

    @NotNull
    private Long sum;
}
