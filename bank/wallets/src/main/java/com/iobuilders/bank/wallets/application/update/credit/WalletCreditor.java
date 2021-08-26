package com.iobuilders.bank.wallets.application.update.credit;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.wallets.domain.WalletRepository;

import java.math.BigDecimal;

@Service
public final class WalletCreditor {

    private final WalletRepository repository;

    public WalletCreditor(WalletRepository repository) {
        this.repository = repository;
    }

    public void credit(String walletId, BigDecimal amount) {
        final var wallet = repository.findOrFailById(walletId);
        final var walletCredited = wallet.credit(amount);
        repository.save(walletCredited);
    }
}
