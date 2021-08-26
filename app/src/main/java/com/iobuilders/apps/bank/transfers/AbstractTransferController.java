package com.iobuilders.apps.bank.transfers;

import com.iobuilders.bank.shared.infrastructure.GuardClauses;
import com.iobuilders.bank.transfers.application.create.TransferCreatorCommand;

public abstract class AbstractTransferController {

    protected enum TransferType {
        DEBIT,
        CREDIT
    }

    protected void ensureValidInputParameters(String id, TransferBody body) {
        GuardClauses.ensureStringIsNotBlank(id);
        GuardClauses.ensureStringIsNotBlank(body.getWalletId());
        GuardClauses.ensureStringIsNotBlank(body.getAmount());
    }

    protected TransferCreatorCommand buildCommand(String id, TransferBody body) {
        ensureValidInputParameters(id, body);
        return new TransferCreatorCommand(id, body.getWalletId(), body.getAmount());
    }

    protected String buildResponse(String type, String id, TransferBody body) {
        return String.format(
                "%s with id %s registered into wallet %s", type, id, body.getWalletId());
    }
}
