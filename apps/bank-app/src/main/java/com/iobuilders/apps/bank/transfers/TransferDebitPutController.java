package com.iobuilders.apps.bank.transfers;

import com.iobuilders.bank.shared.domain.UuidGenerator;
import com.iobuilders.bank.shared.infrastructure.GuardClauses;
import com.iobuilders.bank.transfers.application.TransferCreatorCommand;
import com.iobuilders.bank.transfers.application.TransferDebitCreator;
import com.iobuilders.bank.transfers.domain.TransferExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class TransferDebitPutController {

    private final UuidGenerator uuidGenerator;
    private final TransferDebitCreator creator;

    public TransferDebitPutController(UuidGenerator uuidGenerator, TransferDebitCreator creator) {
        this.uuidGenerator = uuidGenerator;
        this.creator = creator;
    }

    @PutMapping("/transfers/debit")
    public ResponseEntity<String> debit(@RequestBody TransferBody body) {
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
                        "Debit(withdraw) with id %s registered into wallet %s",
                        id, body.getWalletId()));
    }

    private void ensureValidInputParameters(String id, TransferBody body) {
        GuardClauses.ensureStringIsNotBlank(id);
        GuardClauses.ensureStringIsNotBlank(body.getWalletId());
        GuardClauses.ensureStringIsNotBlank(body.getAmount());
    }
}
