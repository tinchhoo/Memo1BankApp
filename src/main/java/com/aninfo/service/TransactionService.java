package com.aninfo.service;

import com.aninfo.exceptions.DepositNegativeSumException;
import com.aninfo.exceptions.InsufficientFundsException;
import com.aninfo.model.Account;
import com.aninfo.model.Transaction;
import com.aninfo.repository.AccountRepository;
import com.aninfo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class TransactionService {

    public static final float VALOR_MINIMO_PARA_LA_PROMOCION = 2000;
    public static final float VALOR_TOPE = 500;

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Transactional
    public Transaction createTransaction(Long cbu, Double amount, Transaction.TransactionType type) {
        Account account = accountRepository.findAccountByCbu(cbu);
        if (account == null) {
            throw new IllegalArgumentException("account not found");
        }
        if (type == Transaction.TransactionType.DEPOSIT && amount >= VALOR_MINIMO_PARA_LA_PROMOCION) {
            double promotionAmount = amount / 10;
            double maximumPromotionAmount = Math.min(promotionAmount, VALOR_TOPE);
            amount += maximumPromotionAmount;
        }

        Transaction transaction = new Transaction(amount, account.getCbu(), type);
        Collection<Transaction> transactions = account.getTransactions();
        transactions.add(transaction);
        if (type == Transaction.TransactionType.DEPOSIT) { accountService.deposit(cbu, amount); }
        else { accountService.withdraw(amount, cbu); }

        transactionRepository.save(transaction);

        return transaction;
    }

    @Transactional
    public void deleteTransaction(long cbu, Long transactionId) {

        Optional<Transaction> transactionOptional = transactionRepository.findById(transactionId);

        if (transactionOptional.isPresent()) {
            Transaction transaction = transactionOptional.get();
            Account account = accountRepository.findAccountByCbu(cbu);
            Collection<Transaction> transactions = account.getTransactions();
            transactions.remove(transaction);
            accountRepository.save(account);
            transactionRepository.delete(transaction);
        } else { throw new IllegalArgumentException("transaction not found"); }

    }

    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }

    public Collection<Transaction> getAccountTransactions(Long cbu) {
        Account account = accountRepository.findAccountByCbu(cbu);
        if (account == null) { throw new IllegalArgumentException("account not found"); }

        return account.getTransactions();
    }

}

