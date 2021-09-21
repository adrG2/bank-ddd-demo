package com.bank.wallets.application.update.credit;

import com.bank.shared.domain.Service;
import com.bank.shared.domain.bus.event.DomainEventSubscriber;
import com.bank.transfers.domain.TransferCreditCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({TransferCreditCreated.class})
public final class CreditOnTransferCreated {

    private static final Logger logger =
            LoggerFactory.getLogger(String.valueOf(CreditOnTransferCreated.class));
    private final WalletCreditCreator creditor;

    public CreditOnTransferCreated(WalletCreditCreator creditor) {
        this.creditor = creditor;
    }

    @EventListener
    public void on(TransferCreditCreated event) {
        logger.debug("{} received: {}", event.eventName(), event);
        creditor.create(event.getWalletId(), event.getAmount());
    }
}
