package com.iobuilders.bank.wallets.domain;

public interface WalletRepository {
    void save(Wallet wallet);

    Wallet findOrFailById(String id);
}
