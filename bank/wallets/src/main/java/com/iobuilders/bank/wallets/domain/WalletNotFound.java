package com.iobuilders.bank.wallets.domain;

public final class WalletNotFound extends RuntimeException {
    public WalletNotFound(String id) {
        super(String.format("Wallet with id %s not exists", id));
    }
}
