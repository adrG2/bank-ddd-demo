package com.bank.wallets.application.update.credit;

import com.bank.shared.domain.Service;
import com.bank.wallets.domain.WalletRepository;

import java.math.BigDecimal;

@Service
public final class WalletCreditCreator {

    private final WalletRepository repository;

    public WalletCreditCreator(WalletRepository repository) {
        this.repository = repository;
    }

    public void create(String walletId, BigDecimal amount) {
        final var wallet = repository.findOrFailById(walletId);
        final var walletCredited = wallet.credit(amount);
        repository.save(walletCredited);
    }
}
