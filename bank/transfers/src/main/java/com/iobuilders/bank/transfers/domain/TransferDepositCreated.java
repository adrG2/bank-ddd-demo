package com.iobuilders.bank.transfers.domain;

import com.iobuilders.bank.shared.domain.bus.event.DomainEvent;

import java.util.Objects;

public class TransferDepositCreated extends DomainEvent {

    private final String walletId;
    private final String amount;

    public TransferDepositCreated(String transferId, String walletId, String amount) {
        super(transferId);
        this.walletId = walletId;
        this.amount = amount;
    }

    @Override
    public String eventName() {
        return "transfer.deposit.created";
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
        TransferDepositCreated that = (TransferDepositCreated) o;
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
