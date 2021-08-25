package com.iobuilders.bank.wallets.application;

public final class WalletCreatorCommand {

    private final String id;
    private final String customerId;

    private WalletCreatorCommand(String id, String customerId) {
        this.id = id;
        this.customerId = customerId;
    }

    public static WalletCreatorCommand create(String id, String customerId) {
        return new WalletCreatorCommand(id, customerId);
    }

    public String getId() {
        return this.id;
    }

    public String getCustomerId() {
        return this.customerId;
    }
}
