package com.matei.customeraccountaggregation.repository;

import com.matei.customeraccountaggregation.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {
    List<Account> getAccountsByName(String name);
}
