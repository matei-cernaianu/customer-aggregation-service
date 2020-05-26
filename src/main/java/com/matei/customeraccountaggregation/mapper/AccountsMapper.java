package com.matei.customeraccountaggregation.mapper;

import com.matei.customeraccountaggregation.dto.AccountDTO;
import com.matei.customeraccountaggregation.entity.Account;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface AccountsMapper {
    Account mapFromDTO(AccountDTO accountDTO);
    AccountDTO mapToDTO(Account account);
    List<Account> mapToEntity(List<AccountDTO> accountDTOS);
    List<AccountDTO> mapToDTO(List<Account> accounts);
}
