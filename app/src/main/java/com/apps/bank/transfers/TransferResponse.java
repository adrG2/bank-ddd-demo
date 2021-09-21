package com.apps.bank.transfers;

public final class TransferResponse {
    private final String transferId;
    private final String walletId;
    private final String amount;

    public TransferResponse(String transferId, String walletId, String amount) {
        this.transferId = transferId;
        this.walletId = walletId;
        this.amount = amount;
    }

    public String getTransferId() {
        return transferId;
    }

    public String getWalletId() {
        return walletId;
    }

    public String getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "TransferResponse{"
                + "transferId='"
                + transferId
                + '\''
                + ", walletId='"
                + walletId
                + '\''
                + ", amount='"
                + amount
                + '\''
                + '}';
    }
}
