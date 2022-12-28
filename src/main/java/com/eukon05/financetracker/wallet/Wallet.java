package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.transaction.Transaction;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "WALLET")
@NoArgsConstructor
public class Wallet {

    public Wallet(String userId, String name, String currency) {
        this.userId = userId;
        this.name = name;
        this.currency = currency;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String userId;

    private String currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wallet_id")
    private final List<Transaction> transactions = new ArrayList<>();

    public BigDecimal getBalance() {
        return getTransactions().stream().map(Transaction::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
