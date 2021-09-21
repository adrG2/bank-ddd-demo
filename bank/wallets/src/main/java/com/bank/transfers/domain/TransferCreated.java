package com.bank.transfers.domain;

import com.bank.shared.domain.bus.event.DomainEvent;

import java.math.BigDecimal;
import java.util.Objects;

public class TransferCreated extends DomainEvent {

    private final String walletId;
    private final BigDecimal amount;

    public TransferCreated(String aggregateId, String walletId, BigDecimal amount) {
        super(aggregateId);
        this.walletId = walletId;
        this.amount = amount;
    }

    @Override
    public String eventName() {
        return "transfer.created";
    }

    public String getWalletId() {
        return walletId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferCreated that = (TransferCreated) o;
        return Objects.equals(walletId, that.walletId) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletId, amount);
    }

    @Override
    public String toString() {
        return "TransferCreated{" + "walletId='" + walletId + '\'' + ", amount=" + amount + '}';
    }
}
