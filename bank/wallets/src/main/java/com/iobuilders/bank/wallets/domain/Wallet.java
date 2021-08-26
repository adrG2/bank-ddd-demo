package com.iobuilders.bank.wallets.domain;

import com.iobuilders.bank.customers.domain.CustomerId;
import com.iobuilders.bank.shared.domain.AggregateRoot;
import com.iobuilders.bank.shared.domain.Money;

import java.math.BigDecimal;
import java.util.Objects;

public final class Wallet extends AggregateRoot {

    private final WalletId id;
    private final CustomerId customerId;
    private final Money balance;

    private Wallet(WalletId id, CustomerId customerId, Money balance) {
        this.id = id;
        this.customerId = customerId;
        this.balance = balance;
    }

    public static Wallet create(String id, String customerId) {
        final var wallet =
                new Wallet(new WalletId(id), new CustomerId(customerId), Money.of(BigDecimal.ZERO));
        wallet.pushEvent(new WalletCreated(id, customerId));
        return wallet;
    }

    public static Wallet createWithInitialBalance(String id, String customerId, BigDecimal amount) {
        Money.ensureAmountIsPositive(amount);
        final var wallet =
                new Wallet(new WalletId(id), new CustomerId(customerId), Money.of(amount));
        wallet.pushEvent(new WalletCreated(id, customerId));
        return wallet;
    }

    public Wallet debit(BigDecimal amount) {
        return new Wallet(id(), customerId(), balance().decrement(amount.abs()));
    }

    public Wallet credit(BigDecimal amount) {
        Money.ensureAmountIsPositive(amount);
        return new Wallet(id(), customerId(), balance().increment(amount));
    }

    public WalletId id() {
        return id;
    }

    public CustomerId customerId() {
        return customerId;
    }

    public Money balance() {
        return balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Wallet wallet = (Wallet) o;
        return Objects.equals(id, wallet.id)
                && Objects.equals(customerId, wallet.customerId)
                && Objects.equals(balance, wallet.balance);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerId, balance);
    }

    @Override
    public String toString() {
        return "Wallet{" + "id=" + id + ", customerId=" + customerId + ", money=" + balance + '}';
    }
}
