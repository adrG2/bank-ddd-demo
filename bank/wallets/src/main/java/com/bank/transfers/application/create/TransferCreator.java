package com.bank.transfers.application.create;

import com.bank.shared.domain.Service;
import com.bank.shared.domain.bus.event.EventBus;
import com.bank.transfers.domain.Transfer;
import com.bank.transfers.domain.TransferRepository;

import java.math.BigDecimal;

@Service
public final class TransferCreator {
    private final EventBus eventBus;
    private final TransferRepository repository;

    public TransferCreator(EventBus eventBus, TransferRepository repository) {
        this.eventBus = eventBus;
        this.repository = repository;
    }

    public void create(TransferCreatorCommand command) {
        final var amount = new BigDecimal(command.getAmount());
        final var transfer = Transfer.create(command.getId(), command.getWalletId(), amount);
        repository.save(transfer);
        eventBus.publish(transfer.pullEvents());
    }
}
