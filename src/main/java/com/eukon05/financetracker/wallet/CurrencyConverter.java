package com.eukon05.financetracker.wallet;

import java.math.BigDecimal;
import java.time.Instant;

public interface CurrencyConverter {

    BigDecimal convert(String from, String to, BigDecimal amount, Instant date);

}
