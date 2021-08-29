package com.iobuilders.apps.bank.transfers;

import com.iobuilders.bank.shared.domain.UuidGenerator;
import com.iobuilders.bank.shared.infrastructure.GuardClauses;
import com.iobuilders.bank.transfers.application.create.TransferCreator;
import com.iobuilders.bank.transfers.application.create.TransferCreatorCommand;
import com.iobuilders.bank.transfers.domain.TransferExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractTransferController {

    private final UuidGenerator uuidGenerator;
    private final TransferCreator creator;

    protected AbstractTransferController(UuidGenerator uuidGenerator, TransferCreator creator) {
        this.uuidGenerator = uuidGenerator;
        this.creator = creator;
    }

    protected ResponseEntity<TransferResponse> execute(TransferBody body) {
        try {
            final var id = uuidGenerator.generate();
            final var command = buildCommand(id, body);
            creator.create(command);
            final var response = new TransferResponse(id, body.getWalletId(), body.getAmount());
            return ResponseEntity.ok(response);
        } catch (TransferExists ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

    private TransferCreatorCommand buildCommand(String id, TransferBody body) {
        GuardClauses.ensureStringIsNotBlank(id);
        GuardClauses.ensureStringIsNotBlank(body.getWalletId());
        GuardClauses.ensureStringIsNotBlank(body.getAmount());
        return new TransferCreatorCommand(id, body.getWalletId(), body.getAmount());
    }
}
