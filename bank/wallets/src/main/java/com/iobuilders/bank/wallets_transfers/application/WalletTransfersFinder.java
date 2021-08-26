package com.iobuilders.bank.wallets_transfers.application;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.transfers.domain.TransferRepository;
import com.iobuilders.bank.wallets.domain.WalletRepository;
import com.iobuilders.bank.wallets_transfers.domain.WalletTransfers;

@Service
public final class WalletTransfersFinder {

    private final WalletRepository walletRepository;
    private final TransferRepository transferRepository;

    public WalletTransfersFinder(
            WalletRepository walletRepository, TransferRepository transferRepository) {
        this.walletRepository = walletRepository;
        this.transferRepository = transferRepository;
    }

    public WalletTransfers find(String walletId) {
        final var wallet = walletRepository.findOrFailById(walletId);
        final var transfers = transferRepository.findOrFailAllByWalletId(walletId);
        return new WalletTransfers(wallet, transfers);
    }
}
