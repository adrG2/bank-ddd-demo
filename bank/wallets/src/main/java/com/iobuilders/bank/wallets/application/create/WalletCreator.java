package com.iobuilders.bank.wallets.application.create;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.shared.domain.bus.event.EventBus;
import com.iobuilders.bank.wallets.domain.Wallet;
import com.iobuilders.bank.wallets.domain.WalletRepository;

@Service
public final class WalletCreator {
    private final EventBus eventBus;
    private final WalletRepository repository;

    public WalletCreator(EventBus eventBus, WalletRepository repository) {
        this.eventBus = eventBus;
        this.repository = repository;
    }

    public void create(WalletCreatorCommand command) {
        final var wallet = Wallet.create(command.getId(), command.getCustomerId());
        repository.save(wallet);
        eventBus.publish(wallet.pullEvents());
    }
}
