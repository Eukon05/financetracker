package com.eukon05.financetracker.wallet.projection;

import java.math.BigDecimal;

public interface WalletStatisticProjection {

    long getCategoryID();

    BigDecimal getSum();

}
