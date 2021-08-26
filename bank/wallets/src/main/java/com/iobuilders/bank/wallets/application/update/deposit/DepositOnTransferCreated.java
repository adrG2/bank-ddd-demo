package com.iobuilders.bank.wallets.application.update.deposit;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.shared.domain.bus.event.DomainEventSubscriber;
import com.iobuilders.bank.transfers.domain.TransferDebitCreated;
import org.springframework.context.event.EventListener;

@Service
@DomainEventSubscriber({TransferDebitCreated.class})
public final class DepositOnTransferCreated {

    private final WalletDebit depositor;

    public DepositOnTransferCreated(WalletDebit depositor) {
        this.depositor = depositor;
    }

    @EventListener
    public void on(TransferDebitCreated event) {
        depositor.debit(event.getWalletId(), event.getAmount());
    }
}
