package com.iobuilders.bank.transfers.domain;

import java.util.List;

public interface TransferRepository {
    Transfer findOrFailByTransferId(String transferId);

    List<Transfer> findOrFailAllByWalletId(String walletId);

    void save(Transfer transfer);
}
