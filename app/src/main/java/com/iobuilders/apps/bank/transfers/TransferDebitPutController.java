package com.iobuilders.apps.bank.transfers;

import com.iobuilders.bank.shared.domain.UuidGenerator;
import com.iobuilders.bank.transfers.application.create.TransferDebitCreator;
import com.iobuilders.bank.transfers.domain.TransferExists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class TransferDebitPutController extends AbstractTransferController {

    private static Logger logger = LoggerFactory.getLogger(TransferDebitPutController.class);

    private final UuidGenerator uuidGenerator;
    private final TransferDebitCreator creator;

    public TransferDebitPutController(UuidGenerator uuidGenerator, TransferDebitCreator creator) {
        this.uuidGenerator = uuidGenerator;
        this.creator = creator;
    }

    @PutMapping("/transfers/debit")
    public ResponseEntity<String> debit(@RequestBody TransferBody body) {
        final var id = uuidGenerator.generate();
        try {
            creator.create(buildCommand(id, body));
        } catch (TransferExists ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getLocalizedMessage());
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(buildResponse(TransferType.DEBIT.name(), id, body));
    }
}
