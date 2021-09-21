package com.bank.wallets.application.find;

import com.bank.shared.domain.Service;
import com.bank.wallets.domain.Wallet;
import com.bank.wallets.domain.WalletRepository;

@Service
public final class WalletFinderById {

    private final WalletRepository repository;

    public WalletFinderById(WalletRepository repository) {
        this.repository = repository;
    }

    public Wallet find(String id) {
        return this.repository.findOrFailById(id);
    }
}
