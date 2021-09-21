package com.apps.bank.transfers;

import com.bank.shared.domain.Money;
import com.bank.shared.domain.UuidGenerator;
import com.bank.transfers.application.create.TransferCreator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public final class TransferCreditPutController extends AbstractTransferController {

    private static final Logger logger = LoggerFactory.getLogger(TransferCreditPutController.class);

    public TransferCreditPutController(UuidGenerator uuidGenerator, TransferCreator creator) {
        super(uuidGenerator, creator);
    }

    @PutMapping("/transfers/credit")
    public ResponseEntity<TransferResponse> credit(@RequestBody TransferBody body) {
        try {
            final var amount = new BigDecimal(body.getAmount());
            Money.ensureAmountIsPositive(amount);
        } catch (IllegalArgumentException ex) {
            logger.error(ex.getMessage());
            return ResponseEntity.badRequest().build();
        }
        return execute(body);
    }
}
