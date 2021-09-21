package com.bank.transfers.application.find;

import com.bank.shared.domain.Service;
import com.bank.transfers.domain.Transfer;
import com.bank.transfers.domain.TransferRepository;

import java.util.List;

@Service
public class TransferFinderAll {

    private final TransferRepository repository;

    public TransferFinderAll(TransferRepository repository) {
        this.repository = repository;
    }

    public List<Transfer> find(String walletId) {
        return repository.findAllByWalletId(walletId);
    }
}
