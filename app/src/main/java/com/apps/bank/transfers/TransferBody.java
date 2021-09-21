package com.apps.bank.transfers;

public final class TransferBody {
    private final String walletId;
    private final String amount;

    public TransferBody(String walletId, String amount) {
        this.walletId = walletId;
        this.amount = amount;
    }

    public String getWalletId() {
        return walletId;
    }

    public String getAmount() {
        return amount;
    }
}
