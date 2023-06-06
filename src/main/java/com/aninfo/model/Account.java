package com.aninfo.model;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.*;
import javax.persistence.OneToMany;


@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cbu;

    private Double balance;

    public Account(){
    }

    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Collection<Transaction> getTransactions() { return transactions; }

    public Account(Double balance) {
        this.balance = balance;
    }

    public Long getCbu() {
        return cbu;
    }

    public void setCbu(Long cbu) {
        this.cbu = cbu;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

}
