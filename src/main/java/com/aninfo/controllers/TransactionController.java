package com.aninfo.controllers;

import com.aninfo.model.Transaction;
import com.aninfo.service.TransactionService;
import com.aninfo.dto.TransactionRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/{cbu}")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction createTransaction(@PathVariable("cbu") Long cbu, @RequestBody TransactionRequestDto requestDto) {
        double amount = requestDto.getAmount();
        Transaction.TransactionType type = requestDto.getType();
        return transactionService.createTransaction(cbu, amount, type);
    }

    @GetMapping("/accounts/{cbu}")
    public Collection<Transaction> getAccountTransactions(@PathVariable Long cbu) {
        return transactionService.getAccountTransactions(cbu);
    }

    @DeleteMapping("/{transactionId}")
    public void deleteTransaction(@PathVariable Long cbu, @RequestParam Long transactionId) {
        transactionService.deleteTransaction(cbu, transactionId);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Long id) {
        Optional<Transaction> transactionOptional = transactionService.getTransactionById(id);
        return ResponseEntity.of(transactionOptional);
    }

}