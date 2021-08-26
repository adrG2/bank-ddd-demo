package com.iobuilders.bank.wallets.domain;

public class WalletExists extends RuntimeException {
    public WalletExists(String value) {
        super(String.format("Wallet with id %s already exists", value));
    }
}
