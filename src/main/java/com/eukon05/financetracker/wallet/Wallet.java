package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.transaction.TransactionType;
import com.eukon05.financetracker.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "WALLET")
@NoArgsConstructor
public class Wallet {

    public Wallet(String name, String currency) {
        this.name = name;
        this.currency = currency;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User user;

    private String currency;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wallet_id")
    private final List<Transaction> transactions = new ArrayList<>();

    public BigDecimal getBalance() {
        BigDecimal income = getTransactions().stream()
                .filter(transaction -> transaction.getType().equals(TransactionType.INCOME))
                .map(Transaction::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return income.subtract(getTransactions().stream()
                .filter(transaction -> transaction.getType().equals(TransactionType.EXPENSE))
                .map(Transaction::getValue)
                .reduce(BigDecimal.ZERO, BigDecimal::add));
    }

}
