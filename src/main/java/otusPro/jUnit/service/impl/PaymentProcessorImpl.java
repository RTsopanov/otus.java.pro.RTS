package otusPro.jUnit.service.impl;


import otusPro.jUnit.entity.*;
import otusPro.jUnit.service.*;
import otusPro.jUnit.service.exception.AccountException;

import java.math.BigDecimal;

public class PaymentProcessorImpl implements PaymentProcessor {
    private AccountService accountService;

    public PaymentProcessorImpl(AccountService accountService) {
        this.accountService = accountService;
    }

    public boolean makeTransfer(Agreement source, Agreement destination, int sourceType,
                                int destinationType, BigDecimal amount) {

        Account sourceAccount = accountService.getAccounts(source).stream()
                .filter(account -> account.getType() == sourceType)
                .findAny()
                .orElseThrow(() -> new AccountException("Account not found"));

        Account destinationAccount = accountService.getAccounts(destination).stream()
                .filter(account -> account.getType() == destinationType)
                .findAny()
                .orElseThrow(() -> new AccountException("Account not found"));

        return accountService.makeTransfer(sourceAccount.getId(), destinationAccount.getId(), amount);
    }

    @Override
    public boolean makeTransferWithComission(Agreement source, Agreement destination,
                                             int sourceType, int destinationType,
                                             BigDecimal amount,
                                             BigDecimal comissionPercent) {

        Account sourceAccount = accountService.getAccounts(source).stream()
                .filter(account -> account.getType() == sourceType)
                .findAny()
                .orElseThrow(() -> new AccountException("Account not found"));

        Account destinationAccount = accountService.getAccounts(destination).stream()
                .filter(account -> account.getType() == destinationType)
                .findAny()
                .orElseThrow(() -> new AccountException("Account not found"));

        accountService.charge(sourceAccount.getId(), amount.negate().multiply(comissionPercent));

        return accountService.makeTransfer(sourceAccount.getId(), destinationAccount.getId(), amount);
    }
}