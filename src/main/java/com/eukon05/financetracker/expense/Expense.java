package com.eukon05.financetracker.expense;

import com.eukon05.financetracker.wallet.Wallet;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "EXPENSE")
@NoArgsConstructor
public class Expense {

    public Expense(String name, BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private BigDecimal value;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Wallet wallet;

}
