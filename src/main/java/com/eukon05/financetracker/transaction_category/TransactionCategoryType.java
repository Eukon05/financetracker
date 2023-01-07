package com.eukon05.financetracker.transaction_category;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum TransactionCategoryType {
    EXPENSE(-1), INCOME(1), DEFAULT(0);

    public final int sign;

}
