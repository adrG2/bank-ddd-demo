package com.iobuilders.bank.wallets.domain;

import com.iobuilders.bank.customers.domain.CustomerId;
import com.iobuilders.bank.shared.domain.AggregateRoot;
import com.iobuilders.bank.shared.domain.Money;

import java.math.BigDecimal;

public final class Wallet extends AggregateRoot {

    private final WalletId id;
    private final CustomerId customerId;
    private final Money money;

    private Wallet(WalletId id, CustomerId customerId, Money money) {
        this.id = id;
        this.customerId = customerId;
        this.money = money;
    }

    public static Wallet create(String id, String customerId) {
        final var wallet =
                new Wallet(new WalletId(id), new CustomerId(customerId), Money.of(BigDecimal.ZERO));
        wallet.pushEvent(new WalletCreated(id, customerId));
        return wallet;
    }

    public static Wallet createWithInitialBalance(String id, String customerId, BigDecimal amount) {
        ensureAmountIsPositive(amount);
        final var wallet =
                new Wallet(new WalletId(id), new CustomerId(customerId), Money.of(amount));
        wallet.pushEvent(new WalletCreated(id, customerId));
        return wallet;
    }

    private static void ensureAmountIsPositive(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Initial balacen must be positive");
        }
    }

    //    public void deposit(BigDecimal amount) {
    //        this.money = money().increment(amount);
    //    }
    //
    //    public void withDraw(BigDecimal amount) {
    //        this.money = money().decrement(amount);
    //    }

    public WalletId id() {
        return id;
    }

    public CustomerId customerId() {
        return customerId;
    }

    public Money money() {
        return money;
    }
}
