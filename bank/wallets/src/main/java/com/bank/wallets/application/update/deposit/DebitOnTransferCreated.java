package com.bank.wallets.application.update.deposit;

import com.bank.shared.domain.Service;
import com.bank.shared.domain.bus.event.DomainEventSubscriber;
import com.bank.transfers.domain.TransferDebitCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({TransferDebitCreated.class})
public final class DebitOnTransferCreated {

    private static final Logger logger = LoggerFactory.getLogger(DebitOnTransferCreated.class);
    private final WalletDebitCreator debtor;

    public DebitOnTransferCreated(WalletDebitCreator debtor) {
        this.debtor = debtor;
    }

    @EventListener
    public void on(TransferDebitCreated event) {
        logger.debug("{} received: {}", event.eventName(), event);
        debtor.create(event.getWalletId(), event.getAmount());
    }
}
