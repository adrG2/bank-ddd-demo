package com.bank.wallets_transfers.domain;

public class TransfersNotFount extends RuntimeException {

    public TransfersNotFount() {
        super("Transfer not fount");
    }
}
