package com.iobuilders.bank.wallets_transfers.domain;

import com.iobuilders.bank.transfers.domain.Transfer;
import com.iobuilders.bank.wallets.domain.Wallet;

import java.util.List;

public final class WalletTransfers {
    private final Wallet wallet;
    private final List<Transfer> transfers;

    public WalletTransfers(Wallet wallet, List<Transfer> transfers) {
        this.wallet = wallet;
        this.transfers = transfers;
    }

    public Wallet getWallet() {
        return wallet;
    }

    public List<Transfer> getTransfers() {
        return transfers;
    }
}
