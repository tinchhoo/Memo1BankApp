package com.aninfo.model;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    public enum TransactionType {
        DEPOSIT, WITHDRAW
    }

    private Long cbuAccount;
    private Double amount;

    public Transaction() {}

    public Transaction(Double amount, long cbu, TransactionType transactionType) {
        this.amount = amount;
        this.cbuAccount = cbu;
        this.transactionType = transactionType;
    }

    public long getCbuAccount() { return cbuAccount; }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double sum) { this.amount = amount; }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

}