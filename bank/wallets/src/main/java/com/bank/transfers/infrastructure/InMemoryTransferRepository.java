package com.bank.transfers.infrastructure;

import com.bank.shared.domain.Service;
import com.bank.transfers.domain.Transfer;
import com.bank.transfers.domain.TransferExists;
import com.bank.transfers.domain.TransferRepository;
import com.bank.wallets_transfers.domain.TransfersNotFount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public final class InMemoryTransferRepository implements TransferRepository {

    private static final Logger logger = LoggerFactory.getLogger(InMemoryTransferRepository.class);
    private static final Map<String, Transfer> transfers = new HashMap<>();

    @Override
    public Transfer findOrFailByTransferId(String transferId) {
        return findByTransferId(transferId);
    }

    private Transfer findByTransferId(String id) {
        return transfers.get(id);
    }

    @Override
    public List<Transfer> findAllByWalletId(String walletId) {
        return InMemoryTransferRepository.transfers.values().stream()
                .filter(transfer -> compareWalletId(transfer, walletId))
                .collect(Collectors.toList());
    }

    private void ensureTransfersFound(List<Transfer> transfers) {
        if (transfers.isEmpty()) {
            throw new TransfersNotFount();
        }
    }

    // TODO Improve
    private static boolean compareWalletId(Transfer transfer, String walletId) {
        final var transferWalletId = transfer.walletId().value();
        return transferWalletId.equals(walletId);
    }

    @Override
    public void save(Transfer transfer) {
        ensureTransferNotExists(transfer);
        final var id = transfer.id();
        transfers.put(id.value(), transfer);
        logger.debug("Transfer with id {} saved", id.value());
    }

    private void ensureTransferNotExists(Transfer transfer) {
        final var id = transfer.id().value();
        if (findByTransferId(id) != null) {
            throw new TransferExists(id);
        }
    }
}
