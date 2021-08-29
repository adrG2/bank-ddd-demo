package com.iobuilders.bank.transfers.domain;

import java.math.BigDecimal;

public class TransferCreditCreated extends TransferCreated {

    public TransferCreditCreated(String transferId, String walletId, BigDecimal amount) {
        super(transferId, walletId, amount);
    }

    public static TransferCreditCreated from(Transfer transfer) {
        return new TransferCreditCreated(
                transfer.id().value(), transfer.walletId().value(), transfer.money().amount());
    }

    @Override
    public String eventName() {
        return "transfer.credit.created";
    }
}
