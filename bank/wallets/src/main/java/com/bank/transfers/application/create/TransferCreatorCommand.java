package com.bank.transfers.application.create;

public final class TransferCreatorCommand {

    private final String id;
    private final String walletId;
    private final String amount;

    public TransferCreatorCommand(String id, String walletId, String amount) {
        this.id = id;
        this.walletId = walletId;
        this.amount = amount;
    }

    public String getId() {
        return id;
    }

    public String getWalletId() {
        return walletId;
    }

    public String getAmount() {
        return amount;
    }
}
