package com.bank.transfers.domain;

import java.util.List;

public interface TransferRepository {
    Transfer findOrFailByTransferId(String transferId);

    List<Transfer> findAllByWalletId(String walletId);

    void save(Transfer transfer);
}
