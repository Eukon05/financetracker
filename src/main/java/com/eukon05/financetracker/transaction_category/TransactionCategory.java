package com.eukon05.financetracker.transaction_category;

import com.eukon05.financetracker.transaction.Transaction;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "CATEGORY")
@NoArgsConstructor
public class TransactionCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private TransactionCategoryType type;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private final List<Transaction> transactions = new ArrayList<>();

}
