package com.aninfo.integration.cucumber;

import com.aninfo.model.Account;
import com.aninfo.Memo1BankApp;
import com.aninfo.model.Transaction;
import com.aninfo.service.TransactionService;
import com.aninfo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

@ContextConfiguration(classes = Memo1BankApp.class)
@WebAppConfiguration
public class AccountIntegrationServiceTest {

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionService transactionService;

    Account createAccount(Double balance) {
        return accountService.createAccount(new Account(balance));
    }


    Account withdraw(Account account, Double sum) {
        Transaction.TransactionType type = Transaction.TransactionType.WITHDRAW;
        transactionService.createTransaction(account.getCbu(), sum, type);
        return accountService.withdraw(account.getCbu(), sum);
    }

    Account deposit(Account account, Double sum) {
        Transaction.TransactionType type = Transaction.TransactionType.DEPOSIT;
        transactionService.createTransaction(account.getCbu(), sum, type);
        return accountService.deposit(account.getCbu(), sum);
    }

}
