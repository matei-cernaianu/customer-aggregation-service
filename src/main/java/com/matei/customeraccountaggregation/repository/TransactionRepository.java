package com.matei.customeraccountaggregation.repository;

import com.matei.customeraccountaggregation.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findAllByAccountIdInOrderByUpdateDesc(List<UUID> accountIds, Pageable pageable);
}
