package com.iobuilders.apps.bank.transfers;

import com.iobuilders.bank.shared.domain.Money;
import com.iobuilders.bank.shared.domain.UuidGenerator;
import com.iobuilders.bank.transfers.application.create.TransferCreator;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public final class TransferCreditPutController extends AbstractTransferController {

    public TransferCreditPutController(UuidGenerator uuidGenerator, TransferCreator creator) {
        super(uuidGenerator, creator);
    }

    @PutMapping("/transfers/credit")
    public ResponseEntity<TransferResponse> credit(@RequestBody TransferBody body) {
        try {
            final var amount = new BigDecimal(body.getAmount());
            Money.ensureAmountIsPositive(amount);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
        return execute(body);
    }
}
