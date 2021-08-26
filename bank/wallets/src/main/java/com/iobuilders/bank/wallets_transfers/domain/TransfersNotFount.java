package com.iobuilders.bank.wallets_transfers.domain;

public class TransfersNotFount extends RuntimeException {

    // TODO improve
    public TransfersNotFount() {
        super("Transfer not fount");
    }
}
