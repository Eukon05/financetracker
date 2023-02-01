package com.eukon05.financetracker.transaction;

import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class TransactionSpecification {
    private TransactionSpecification() {
    }

    static final String CATEGORY = "category";
    private static final String FROM_DATE = "fromDate";
    private static final String TO_DATE = "toDate";
    private static final String VALUE = "value";
    private static final String CREATED_AT = "createdAt";
    private static final String ID = "id";
    private static final String WALLET = "wallet";

    private static Specification<Transaction> createdAfter(Instant from) {
        return (root, query, cb) -> cb.greaterThanOrEqualTo(root.get(CREATED_AT), from);
    }

    private static Specification<Transaction> createdBefore(Instant to) {
        return (root, query, cb) -> cb.lessThanOrEqualTo(root.get(CREATED_AT), to);
    }

    private static Specification<Transaction> categoryIdEqualTo(long categoryID) {
        return (root, query, cb) -> cb.equal(root.get(CATEGORY).get(ID), categoryID);
    }

    private static Specification<Transaction> walletIdEqualTo(long walletID) {
        return (root, query, cb) -> cb.equal(root.get(WALLET).get(ID), walletID);
    }

    private static Specification<Transaction> valueEqualTo(BigDecimal value) {
        return (root, query, cb) -> cb.equal(root.get(VALUE), value);
    }

    static Specification<Transaction> build(long walletID, Map<String, String> params) {
        final List<Specification<Transaction>> specs = new ArrayList<>();
        specs.add(walletIdEqualTo(walletID));

        if (params.containsKey(CATEGORY)) {
            specs.add(categoryIdEqualTo(Long.parseLong(params.get(CATEGORY))));
        }

        if (params.containsKey(FROM_DATE)) {
            specs.add(createdAfter(Instant.parse(params.get(FROM_DATE))));
        }

        if (params.containsKey(TO_DATE)) {
            specs.add(createdBefore(Instant.parse(params.get(TO_DATE))));
        }

        if (params.containsKey(VALUE)) {
            specs.add(valueEqualTo(new BigDecimal(params.get(VALUE))));
        }

        return specs.stream().reduce(Specification::and).orElse(null);
    }
}
