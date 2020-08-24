package com.matei.customeraccountaggregation.repository;

import com.matei.customeraccountaggregation.entity.Alert;
import com.matei.customeraccountaggregation.entity.pk.AlertPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AlertRepository extends JpaRepository<Alert, AlertPK> {
    List<Alert> findAllByAlertPK_User_UserId(Long userId);
    Alert findByAlertPK_User_UserIdAndAlertPK_AccountId(Long userId, UUID accountId);
    Integer deleteAlertByAlertPK_AccountId(UUID accountId);
}
