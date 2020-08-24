package com.matei.customeraccountaggregation.entity;

import com.matei.customeraccountaggregation.entity.pk.AlertPK;
import com.matei.customeraccountaggregation.enums.AlertType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Table(name = "alerts")
public class Alert {

    @EmbeddedId
    private AlertPK alertPK;

    @Column(name = "alert_type")
    @Enumerated(EnumType.STRING)
    private AlertType alertType;

    @Column(name = "threshold")
    private Long threshold;

    @Column(name = "created_at")
    @CreationTimestamp
    private Instant creadedAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private Instant updatedAt;
}
