package com.matei.customeraccountaggregation.util;

import com.matei.customeraccountaggregation.entity.Account;
import com.matei.customeraccountaggregation.entity.Transaction;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static void validateUsername(String username) {
        if (!username.matches("^[a-zA-Z0-9._-]{3,}$")) {
            throw new IllegalArgumentException("Invalid username");
        }
    }

    public static boolean validateDate(Instant source) {
        return source.plus(1, ChronoUnit.DAYS)
                .isAfter(Instant.now());
    }

    public static Boolean areAccountsUpdated(List<Account> accounts) {
        return accounts.stream()
                .findFirst()
                .map(account -> validateDate(account.getLastUpdatedTimestamp()))
                .orElse(false);
    }

    public static Boolean areTransactionsUpdated(List<Transaction> transactions) {
        return transactions.stream()
                .findFirst()
                .map(transaction -> validateDate(transaction.getLastUpdatedTimestamp()))
                .orElse(false);
    }
}
