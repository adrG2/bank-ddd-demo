package com.iobuilders.bank.transfers.application.create;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.shared.domain.bus.event.EventBus;
import com.iobuilders.bank.transfers.domain.Transfer;
import com.iobuilders.bank.transfers.domain.TransferRepository;

import java.math.BigDecimal;

@Service
public final class TransferDebitCreator {

    private final EventBus eventBus;
    private final TransferRepository repository;

    public TransferDebitCreator(EventBus eventBus, TransferRepository repository) {
        this.eventBus = eventBus;
        this.repository = repository;
    }

    public void create(TransferCreatorCommand command) {
        final var amount = new BigDecimal(command.getAmount());
        final var transfer = Transfer.debit(command.getId(), command.getWalletId(), amount);
        repository.save(transfer);
        eventBus.publish(transfer.pullEvents());
    }
}
