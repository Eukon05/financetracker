package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.transaction.Transaction;
import com.eukon05.financetracker.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "WALLET")
@NoArgsConstructor
public class Wallet {

    public Wallet(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private User user;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wallet_id")
    private final List<Transaction> transactions = new ArrayList<>();

}
