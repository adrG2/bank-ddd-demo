package com.iobuilders.bank.transfers.infrastructure;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.transfers.domain.Transfer;
import com.iobuilders.bank.transfers.domain.TransferRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public final class InMemoryTransferRepository implements TransferRepository {

    private static final Map<String, Transfer> transfers = new HashMap<>();

    @Override
    public Transfer findOrFailByTransferId(String transferId) {
        return transfers.get(transferId);
    }

    @Override
    public List<Transfer> findOrFailAllByWalletId(String walletId) {
        return transfers.values().stream()
                .filter(transfer -> compareWalletId(transfer, walletId))
                .collect(Collectors.toList());
    }

    // TODO Improve
    private static boolean compareWalletId(Transfer transfer, String walletId) {
        final var transferWalletId = transfer.walletId().value();
        return transferWalletId.equals(walletId);
    }

    @Override
    public void save(Transfer transfer) {
        transfers.put(transfer.id().value(), transfer);
    }
}
