package com.iobuilders.bank.wallets.domain;

import com.iobuilders.bank.shared.domain.bus.event.DomainEvent;

public class WalletCreated extends DomainEvent {

    private final String customerId;

    public WalletCreated(String id, String customerId) {
        super(id);
        this.customerId = customerId;
    }

    @Override
    public String eventName() {
        return "wallet.created";
    }

    public String getCustomerId() {
        return customerId;
    }
}
