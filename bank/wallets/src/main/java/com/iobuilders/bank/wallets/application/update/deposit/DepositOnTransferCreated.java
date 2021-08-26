package com.iobuilders.bank.wallets.application.update.deposit;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.shared.domain.bus.event.DomainEventSubscriber;
import com.iobuilders.bank.transfers.domain.TransferDebitCreated;
import com.iobuilders.bank.wallets.application.update.credit.CreditOnTransferCreated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({TransferDebitCreated.class})
public final class DepositOnTransferCreated {

    private static final Logger logger =
            LoggerFactory.getLogger(String.valueOf(CreditOnTransferCreated.class));
    private final WalletDebit depositor;

    public DepositOnTransferCreated(WalletDebit depositor) {
        this.depositor = depositor;
    }

    @EventListener
    public void on(TransferDebitCreated event) {
        logger.debug("{} received: {}", event.eventName(), event);
        depositor.debit(event.getWalletId(), event.getAmount());
    }
}
