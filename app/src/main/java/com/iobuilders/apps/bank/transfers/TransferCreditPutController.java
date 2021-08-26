package com.iobuilders.apps.bank.transfers;

import com.iobuilders.bank.shared.domain.UuidGenerator;
import com.iobuilders.bank.transfers.application.create.TransferCreditCreator;
import com.iobuilders.bank.transfers.domain.TransferExists;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class TransferCreditPutController extends AbstractTransferController {

    private final UuidGenerator uuidGenerator;
    private final TransferCreditCreator creator;

    public TransferCreditPutController(UuidGenerator uuidGenerator, TransferCreditCreator creator) {
        this.uuidGenerator = uuidGenerator;
        this.creator = creator;
    }

    @PutMapping("/transfers/credit")
    public ResponseEntity<String> credit(@RequestBody TransferBody body) {
        final var id = uuidGenerator.generate();
        try {
            creator.create(buildCommand(id, body));
        } catch (TransferExists ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getLocalizedMessage());
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(buildResponse(TransferType.CREDIT.name(), id, body));
    }
}
