package com.matei.customeraccountaggregation.entity;

import com.matei.customeraccountaggregation.dto.Creditor;
import com.matei.customeraccountaggregation.dto.Debtor;
import com.matei.customeraccountaggregation.dto.ExchangeRate;
import com.matei.customeraccountaggregation.dto.OriginalAmount;
import com.matei.customeraccountaggregation.enums.CurrencyCode;
import com.matei.customeraccountaggregation.enums.TransactionStatus;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import org.hibernate.annotations.TypeDefs;
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
@Table(name = "transactions")
@TypeDefs({
        @TypeDef(name = "json", typeClass = JsonStringType.class),
        @TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
})
public class Transaction {

    @Id
    @Column(columnDefinition = "BINARY(16)")
    private UUID id;

    @Column(name = "account_id", columnDefinition = "BINARY(16)")
    private UUID accountId;

    @Type(type = "json")
    @Column(name = "exchange_rate")
    private ExchangeRate exchangeRate;

    @Type(type = "json")
    @Column(name = "original_amount")
    private OriginalAmount originalAmount;

    @Type(type = "json")
    @Column(name = "creditor")
    private Creditor creditor;

    @Type(type = "json")
    @Column(name = "deptor")
    private Debtor debtor;

    @Column
    private TransactionStatus status;

    @Column(name = "currency_code")
    private CurrencyCode currency;

    @Column(precision = 9, scale = 3)
    private BigDecimal amount;

    @Column
    private Instant update;

    @Column
    private String description;

    @Column(name = "last_updated_timestamp")
    @UpdateTimestamp
    private Instant lastUpdatedTimestamp;
}
