package com.iobuilders.apps.bank.wallets;

import com.iobuilders.bank.wallets.domain.Wallet;

public final class WalletResponse {
    private final String id;
    private final String customerId;
    private final String balance;

    private WalletResponse(String id, String customerId, String balance) {
        this.id = id;
        this.customerId = customerId;
        this.balance = balance;
    }

    public static WalletResponse from(Wallet wallet) {
        return new WalletResponse(
                wallet.id().value(),
                wallet.customerId().value(),
                wallet.balance().amountAsString());
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "WalletResponse{"
                + "id='"
                + id
                + '\''
                + ", customerId='"
                + customerId
                + '\''
                + ", balance='"
                + balance
                + '\''
                + '}';
    }
}
