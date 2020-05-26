package com.matei.customeraccountaggregation.entity;

import com.matei.customeraccountaggregation.enums.AccountStatus;
import com.matei.customeraccountaggregation.enums.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column
    private Instant update;

    @Column
    private String name;

    @Column
    private String product;

    @Column
    private AccountStatus status;

    @Column
    private AccountType type;

    @Column(precision = 31, scale = 12)
    private BigDecimal balance;

    @Column(name = "last_updated_timestamp")
    @UpdateTimestamp
    private Instant lastUpdatedTimestamp;
}
