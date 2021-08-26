package com.iobuilders.bank.wallets.application.update.deposit;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.wallets.domain.WalletRepository;

import java.math.BigDecimal;

@Service
public final class WalletDepositor {

    private final WalletRepository repository;

    public WalletDepositor(WalletRepository repository) {
        this.repository = repository;
    }

    public void deposit(String walletId, BigDecimal amount) {
        final var wallet = repository.findOrFailById(walletId);
        final var walletDeposited = wallet.deposit(amount);
        repository.save(walletDeposited);
    }
}
