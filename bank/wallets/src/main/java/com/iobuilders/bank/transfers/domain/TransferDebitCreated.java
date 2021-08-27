package com.iobuilders.bank.transfers.domain;

import java.math.BigDecimal;

public class TransferDebitCreated extends TransferCreated {

    public TransferDebitCreated(String transferId, String walletId, BigDecimal amount) {
        super(transferId, walletId, amount);
    }

    @Override
    public String eventName() {
        return "transfer.debit.created";
    }
}
