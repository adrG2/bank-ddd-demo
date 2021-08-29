package com.iobuilders.bank.shared.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public final class Money implements Serializable {
    private final BigDecimal amount;
    private final Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money of(BigDecimal amount) {
        return new Money(amount, Currency.getInstance("EUR"));
    }

    public Money increment(BigDecimal amountToAdd) {
        final var amountAdded = amount().add(amountToAdd);
        return new Money(amountAdded, currency());
    }

    public Money decrement(BigDecimal subtrahend) {
        final var amountSubtrahend = amount().subtract(subtrahend);
        return new Money(amountSubtrahend, currency());
    }

    public boolean isNegative(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) <= 0;
    }

    /**
     * Guard clause to verify that a amount is positive
     *
     * @param amount Amount of money
     * @throws IllegalArgumentException when is equal o less than 0
     */
    public static void ensureAmountIsPositive(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(String.format("Amount %s must be positive", amount));
        }
    }

    /**
     * Guard clause to verify that a amount is negative
     *
     * @param amount Amount of money
     * @throws IllegalArgumentException when is equal o greater than 0
     */
    public static void ensureAmountIsNegative(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) >= 0) {
            throw new IllegalArgumentException(String.format("Amount %s must be negative", amount));
        }
    }

    public Currency currency() {
        return currency;
    }

    public BigDecimal amount() {
        return amount;
    }

    public String amountAsString() {
        return amount.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Money money = (Money) o;
        return Objects.equals(currency, money.currency) && Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, amount);
    }

    @Override
    public String toString() {
        return "Money{" + "currency=" + currency + ", amount=" + amount + '}';
    }
}
