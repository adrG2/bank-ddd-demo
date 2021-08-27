package com.iobuilders.bank.transfers.domain;

import java.math.BigDecimal;

public class TransferCreditCreated extends TransferCreated {

    public TransferCreditCreated(String transferId, String walletId, BigDecimal amount) {
        super(transferId, walletId, amount);
    }

    @Override
    public String eventName() {
        return "transfer.credit.created";
    }
}
