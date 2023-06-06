package com.aninfo.dto;


import com.aninfo.model.Transaction;

public class TransactionRequestDto {
    private double amount;
    private Transaction.TransactionType type;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double sum) {
        this.amount = amount;
    }

    public Transaction.TransactionType getType() {
        return type;
    }

    public void setType(Transaction.TransactionType type) {
        this.type = type;
    }

}