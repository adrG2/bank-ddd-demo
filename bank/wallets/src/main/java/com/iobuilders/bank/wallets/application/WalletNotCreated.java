package com.iobuilders.bank.wallets.application;

public final class WalletNotCreated extends RuntimeException {

    public WalletNotCreated(String id) {
        super(String.format("Wallet with id %s not created", id));
    }
}
