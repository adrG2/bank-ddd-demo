package com.iobuilders.bank.transfers.domain;

import com.iobuilders.bank.shared.domain.AggregateRoot;
import com.iobuilders.bank.shared.domain.Money;
import com.iobuilders.bank.wallets.domain.WalletId;

import java.math.BigDecimal;
import java.util.Objects;

public final class Transfer extends AggregateRoot {
    private final TransferId id;
    private final WalletId walletId;
    private final Money money;

    private Transfer(TransferId id, WalletId walletId, Money money) {
        this.id = id;
        this.walletId = walletId;
        this.money = money;
    }

    // deposit
    public static Transfer credit(String id, String walletId, BigDecimal amount) {
        final var transferId = new TransferId(id);
        final var transferWalletId = new WalletId(walletId);
        final var transferMoney = Money.fromPositive(amount);
        final var transfer = new Transfer(transferId, transferWalletId, transferMoney);
        final var event =
                new TransferCreditCreated(
                        transferId.value(), transferId.value(), transferMoney.amount());
        transfer.pushEvent(event);
        return transfer;
    }

    // withdraw
    public static Transfer debit(String id, String walletId, BigDecimal amount) {
        final var transferId = new TransferId(id);
        final var transferWalletId = new WalletId(walletId);
        final var transferMoney = Money.fromNegative(amount);
        final var transfer = new Transfer(transferId, transferWalletId, transferMoney);
        final var event =
                new TransferDebitCreated(
                        transferId.value(), transferId.value(), transferMoney.amount());
        transfer.pushEvent(event);
        return transfer;
    }

    public TransferId id() {
        return this.id;
    }

    public WalletId walletId() {
        return this.walletId;
    }

    public Money money() {
        return this.money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transfer transfer = (Transfer) o;
        return Objects.equals(id, transfer.id)
                && Objects.equals(walletId, transfer.walletId)
                && Objects.equals(money, transfer.money);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, walletId, money);
    }

    @Override
    public String toString() {
        return "Transfer{" + "id=" + id + ", walletId=" + walletId + ", money=" + money + '}';
    }
}