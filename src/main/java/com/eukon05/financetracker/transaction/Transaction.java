package com.eukon05.financetracker.transaction;

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

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    private BigDecimal value;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Wallet wallet;

    @CreationTimestamp
    private Instant createdAt;

}
