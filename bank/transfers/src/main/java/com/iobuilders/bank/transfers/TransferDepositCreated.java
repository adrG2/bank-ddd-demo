package com.iobuilders.bank.transfers;

import com.iobuilders.bank.shared.domain.bus.event.DomainEvent;

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
}
