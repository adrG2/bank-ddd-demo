package com.iobuilders.apps.bank.transfers;

import com.iobuilders.bank.shared.domain.Money;
import com.iobuilders.bank.shared.domain.UuidGenerator;
import com.iobuilders.bank.transfers.application.create.TransferCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public final class TransferDebitPutController extends AbstractTransferController {

    private static final Logger logger = LoggerFactory.getLogger(TransferDebitPutController.class);

    public TransferDebitPutController(UuidGenerator uuidGenerator, TransferCreator creator) {
        super(uuidGenerator, creator);
    }

    @PutMapping("/transfers/debit")
    public ResponseEntity<TransferResponse> debit(@RequestBody TransferBody body) {
        try {
            final var amount = new BigDecimal(body.getAmount());
            Money.ensureAmountIsNegative(amount);
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
        return execute(body);
    }
}
