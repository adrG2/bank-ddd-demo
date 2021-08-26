package com.iobuilders.bank.wallets.application.update.credit;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.shared.domain.bus.event.DomainEvent;
import com.iobuilders.bank.shared.domain.bus.event.DomainEventSubscriber;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({})
public final class CreditOnTransferCreated {

    private final WalletCreditor creditor;

    public CreditOnTransferCreated(WalletCreditor creditor) {
        this.creditor = creditor;
    }

    @EventListener
    public void on(DomainEvent event) {
        creditor.credit(null, null);
    }
}
