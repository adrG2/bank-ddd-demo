package com.iobuilders.bank.wallets.application.update.credit;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.shared.domain.bus.event.DomainEventSubscriber;
import com.iobuilders.bank.transfers.domain.TransferCreditCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({TransferCreditCreated.class})
public final class CreditOnTransferCreated {

    private static final Logger logger =
            LoggerFactory.getLogger(String.valueOf(CreditOnTransferCreated.class));
    private final WalletCreditor creditor;

    public CreditOnTransferCreated(WalletCreditor creditor) {
        this.creditor = creditor;
    }

    @EventListener
    public void on(TransferCreditCreated event) {
        logger.debug("{} received: {}", event.eventName(), event);
        creditor.credit(event.getWalletId(), event.getAmount());
    }
}
