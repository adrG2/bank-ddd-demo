package com.iobuilders.bank.wallets_transfers.domain;

import com.iobuilders.bank.transfers.domain.Transfer;
import com.iobuilders.bank.wallets.domain.Wallet;

import java.util.List;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WalletTransfers that = (WalletTransfers) o;
        return Objects.equals(wallet, that.wallet) && Objects.equals(transfers, that.transfers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(wallet, transfers);
    }

    @Override
    public String toString() {
        return "WalletTransfers{" + "wallet=" + wallet + ", transfers=" + transfers + '}';
    }
}
