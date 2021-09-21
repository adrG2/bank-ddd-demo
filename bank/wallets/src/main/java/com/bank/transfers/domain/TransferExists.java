package com.bank.transfers.domain;

public class TransferExists extends RuntimeException {
    public TransferExists(String id) {
        super(String.format("Transfer with id %s already exists", id));
    }
}
