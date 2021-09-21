package com.bank.wallets.application.update.deposit;

import com.bank.shared.domain.Service;
import com.bank.wallets.domain.WalletRepository;

import java.math.BigDecimal;

@Service
public final class WalletDebitCreator {

    private final WalletRepository repository;

    public WalletDebitCreator(WalletRepository repository) {
        this.repository = repository;
    }

    public void create(String walletId, BigDecimal amount) {
        final var wallet = repository.findOrFailById(walletId);
        final var walletDebited = wallet.debit(amount);
        repository.save(walletDebited);
    }
}
