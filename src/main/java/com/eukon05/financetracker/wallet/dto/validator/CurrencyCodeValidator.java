package com.eukon05.financetracker.wallet.dto.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Currency;
import java.util.Optional;

class CurrencyCodeValidator implements ConstraintValidator<CurrencyCode, String> {

    private boolean isOptional;

    @Override
    public void initialize(CurrencyCode currencyCode) {
        this.isOptional = currencyCode.optional();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        boolean isEmpty = Optional.ofNullable(s).isEmpty();

        if (!isEmpty)
            isEmpty = s.isBlank();

        if (!isEmpty && s.matches("^[A-Z]{3}$")) {
            for (Currency currency : Currency.getAvailableCurrencies()) {
                if (currency.getCurrencyCode().equals(s)) {
                    return true;
                }
            }
        }

        return isOptional && isEmpty;
    }

}
