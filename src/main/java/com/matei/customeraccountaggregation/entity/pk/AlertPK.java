package com.matei.customeraccountaggregation.entity.pk;

import com.matei.customeraccountaggregation.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class AlertPK implements Serializable {

    @Column(name = "account_id")
    private UUID accountId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
