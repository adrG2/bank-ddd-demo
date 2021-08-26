package com.iobuilders.bank.transfers.application.find;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.transfers.domain.Transfer;
import com.iobuilders.bank.transfers.domain.TransferRepository;

import java.util.List;

@Service
public class TransferFinderAll {

    private final TransferRepository repository;

    public TransferFinderAll(TransferRepository repository) {
        this.repository = repository;
    }

    public List<Transfer> find(String walletId) {
        return repository.findOrFailAllByWalletId(walletId);
    }
}
