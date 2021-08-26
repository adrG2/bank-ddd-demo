package com.iobuilders.bank.wallets.application.update.deposit;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.shared.domain.bus.event.DomainEvent;
import com.iobuilders.bank.shared.domain.bus.event.DomainEventSubscriber;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({})
public final class DepositOnTransferCreated {

    private final WalletDepositor depositor;

    public DepositOnTransferCreated(WalletDepositor depositor) {
        this.depositor = depositor;
    }

    @EventListener
    public void on(DomainEvent event) {
        depositor.deposit(null, null);
    }
}
