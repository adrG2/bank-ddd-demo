package com.iobuilders.bank.transfers.domain;

import com.iobuilders.bank.shared.domain.bus.event.DomainEvent;

import java.util.Objects;

public class TransferDebitCreated extends DomainEvent {

    private final String walletId;
    private final String amount;

    public TransferDebitCreated(String transferId, String walletId, String amount) {
        super(transferId);
        this.walletId = walletId;
        this.amount = amount;
    }

    @Override
    public String eventName() {
        return "transfer.debit.created";
    }

    public String getWalletId() {
        return walletId;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransferDebitCreated that = (TransferDebitCreated) o;
        return Objects.equals(walletId, that.walletId) && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(walletId, amount);
    }

    @Override
    public String toString() {
        return "TransferDepositCreated{"
                + "walletId='"
                + walletId
                + '\''
                + ", amount='"
                + amount
                + '\''
                + '}';
    }
}
