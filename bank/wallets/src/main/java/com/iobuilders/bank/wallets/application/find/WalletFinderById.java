package com.iobuilders.bank.wallets.application.find;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.wallets.domain.Wallet;
import com.iobuilders.bank.wallets.domain.WalletRepository;

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
