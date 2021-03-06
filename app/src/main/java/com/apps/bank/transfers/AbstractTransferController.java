package com.apps.bank.transfers;

import com.bank.shared.domain.UuidGenerator;
import com.bank.shared.infrastructure.GuardClauses;
import com.bank.transfers.application.create.TransferCreator;
import com.bank.transfers.application.create.TransferCreatorCommand;
import com.bank.transfers.domain.TransferExists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class AbstractTransferController {

    private static final Logger logger = LoggerFactory.getLogger(AbstractTransferController.class);

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
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());
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
