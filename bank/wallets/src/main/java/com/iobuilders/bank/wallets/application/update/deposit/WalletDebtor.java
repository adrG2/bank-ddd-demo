package com.iobuilders.bank.wallets.application.update.deposit;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.wallets.domain.WalletRepository;

import java.math.BigDecimal;

@Service
public final class WalletDebtor {

    private final WalletRepository repository;

    public WalletDebtor(WalletRepository repository) {
        this.repository = repository;
    }

    public void debit(String walletId, BigDecimal amount) {
        final var wallet = repository.findOrFailById(walletId);
        final var walletDebited = wallet.debit(amount);
        repository.save(walletDebited);
    }
}
