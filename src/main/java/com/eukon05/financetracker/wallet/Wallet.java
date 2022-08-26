package com.eukon05.financetracker.wallet;

import com.eukon05.financetracker.expense.Expense;
import com.eukon05.financetracker.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id")
    private final Set<Expense> expenses = new HashSet<>();

}
