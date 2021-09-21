package com.bank.wallets.application.create;

import com.bank.shared.domain.Service;
import com.bank.shared.domain.bus.event.EventBus;
import com.bank.wallets.domain.Wallet;
import com.bank.wallets.domain.WalletRepository;

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
