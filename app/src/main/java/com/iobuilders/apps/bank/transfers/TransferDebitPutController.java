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
public final class TransferDebitPutController extends AbstractTransferController {

    public TransferDebitPutController(UuidGenerator uuidGenerator, TransferCreator creator) {
        super(uuidGenerator, creator);
    }

    @PutMapping("/transfers/debit")
    public ResponseEntity<TransferResponse> debit(@RequestBody TransferBody body) {
        try {
            final var amount = new BigDecimal(body.getAmount());
            Money.ensureAmountIsNegative(amount);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().build();
        }
        return execute(body);
    }
}
