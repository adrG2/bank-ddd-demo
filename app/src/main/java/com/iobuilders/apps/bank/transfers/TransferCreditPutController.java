package com.iobuilders.apps.bank.transfers;

import com.iobuilders.bank.shared.domain.UuidGenerator;
import com.iobuilders.bank.shared.infrastructure.GuardClauses;
import com.iobuilders.bank.transfers.application.create.TransferCreatorCommand;
import com.iobuilders.bank.transfers.application.create.TransferCreditCreator;
import com.iobuilders.bank.transfers.domain.TransferExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class TransferCreditPutController {

    private final UuidGenerator uuidGenerator;
    private final TransferCreditCreator creator;

    public TransferCreditPutController(UuidGenerator uuidGenerator, TransferCreditCreator creator) {
        this.uuidGenerator = uuidGenerator;
        this.creator = creator;
    }

    @PutMapping("/transfers/credit")
    public ResponseEntity<String> credit(@RequestBody TransferBody body) {
        final var id = uuidGenerator.generate();
        ensureValidInputParameters(id, body);
        final var command = new TransferCreatorCommand(id, body.getWalletId(), body.getAmount());
        try {
            creator.create(command);
        } catch (TransferExists ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getLocalizedMessage());
        }
        return ResponseEntity.ok(
                String.format(
                        "Credit(deposit) with id %s registered into wallet %s",
                        id, body.getWalletId()));
    }

    private void ensureValidInputParameters(String id, TransferBody body) {
        GuardClauses.ensureStringIsNotBlank(id);
        GuardClauses.ensureStringIsNotBlank(body.getWalletId());
        GuardClauses.ensureStringIsNotBlank(body.getAmount());
    }
}
