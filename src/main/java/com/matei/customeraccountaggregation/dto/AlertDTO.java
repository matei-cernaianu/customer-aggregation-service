package com.matei.customeraccountaggregation.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.matei.customeraccountaggregation.enums.AlertType;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@NotNull
public class AlertDTO {

    @NotNull
    private UUID accountId;

    @NotNull
    private Long userId;

    @NotNull
    private AlertType alertType;

    @NotNull
    private Long threshold;

    private Instant createdAt;
    private Instant updatedAt;
}
