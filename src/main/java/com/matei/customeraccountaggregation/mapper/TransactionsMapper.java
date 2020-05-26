package com.matei.customeraccountaggregation.mapper;

import com.matei.customeraccountaggregation.dto.TransactionDTO;
import com.matei.customeraccountaggregation.entity.Transaction;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface TransactionsMapper {
    List<Transaction> maptoEntity(List<TransactionDTO> transactionDTOs);
    List<TransactionDTO> maptoDTO(List<Transaction> transactions);
}
