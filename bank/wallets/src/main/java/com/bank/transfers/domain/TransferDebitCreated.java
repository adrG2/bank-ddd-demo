package com.bank.transfers.domain;

import java.math.BigDecimal;

public class TransferDebitCreated extends TransferCreated {

    public TransferDebitCreated(String transferId, String walletId, BigDecimal amount) {
        super(transferId, walletId, amount);
    }

    public static TransferDebitCreated from(Transfer transfer) {
        return new TransferDebitCreated(
                transfer.id().value(), transfer.walletId().value(), transfer.money().amount());
    }

    @Override
    public String eventName() {
        return "transfer.debit.created";
    }
}
