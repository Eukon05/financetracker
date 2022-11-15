package com.eukon05.financetracker.transaction;

import com.eukon05.financetracker.transaction.exceptions.TransactionTypeMismatchException;
import com.eukon05.financetracker.wallet.Wallet;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@Entity
@Table(name = "TRANSACTION")
@NoArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @ManyToOne
    private TransactionCategory category;

    private BigDecimal value;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Wallet wallet;

    @CreationTimestamp
    private Instant createdAt;

    public void setCategory(TransactionCategory category){
        if (category.getId() != 0 && category.getType().sign != getValue().signum()) {
            throw new TransactionTypeMismatchException();
        }
        this.category = category;
    }

}
