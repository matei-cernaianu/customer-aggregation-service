package com.matei.customeraccountaggregation.service;

import com.matei.customeraccountaggregation.dto.AccountDTO;
import com.matei.customeraccountaggregation.dto.TransactionDTO;
import com.matei.customeraccountaggregation.entity.Transaction;
import com.matei.customeraccountaggregation.mapper.TransactionsMapper;
import com.matei.customeraccountaggregation.repository.TransactionRepository;
import com.matei.customeraccountaggregation.service.external.ExternalTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.matei.customeraccountaggregation.util.ValidationUtils.areTransactionsUpdated;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TransactionsMapper transactionsMapper;

    @Autowired
    private ExternalTransactionService externalTransactionService;

    @Autowired
    private AccountService accountService;

    public List<TransactionDTO> getAccountsTransactions(String username, Integer page, Integer pageSize) {

        final List<AccountDTO> accounts = accountService.getAccountsDetails(username);
        //get all transactions for all user accounts sorted desc by updated and paginated
        final List<Transaction> transactions = fetchTransactions(accounts, page, pageSize);

        //if transactions already fetched today, serve them from local DB
        if (areTransactionsUpdated(transactions)) {
            return transactionsMapper.maptoDTO(transactions);
        }

        //if not, get them from the service and populate the database
        final List<TransactionDTO> newTransactions = externalTransactionService.getNewTransactions(username);
        transactionRepository.saveAll(transactionsMapper.maptoEntity(newTransactions));
        return newTransactions.subList(0, pageSize);
    }

    private List<Transaction> fetchTransactions(List<AccountDTO> accounts, int page, int pageSize) {
        return transactionRepository.findAllByAccountIdInOrderByUpdateDesc(
                accounts.stream()
                        .map(AccountDTO::getId)
                        .collect(Collectors.toList()), createPageRequest(page, pageSize));
    }

    private PageRequest createPageRequest(Integer page, Integer pageSize) {
        return PageRequest.of(page, pageSize);
    }
}
