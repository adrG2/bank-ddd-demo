package com.iobuilders.bank.wallets.application.update.credit;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.shared.domain.bus.event.DomainEventSubscriber;
import com.iobuilders.bank.transfers.domain.TransferCreditCreated;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({TransferCreditCreated.class})
public final class CreditOnTransferCreated {

    private final WalletCreditor creditor;

    public CreditOnTransferCreated(WalletCreditor creditor) {
        this.creditor = creditor;
    }

    @EventListener
    public void on(TransferCreditCreated event) {
        creditor.credit(event.getWalletId(), event.getAmount());
    }
}
