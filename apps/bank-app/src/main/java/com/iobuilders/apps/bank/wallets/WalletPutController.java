package com.iobuilders.apps.bank.wallets;

import com.iobuilders.bank.shared.domain.UuidGenerator;
import com.iobuilders.bank.wallets.application.WalletNotCreated;
import com.iobuilders.bank.wallets.application.create.WalletCreator;
import com.iobuilders.bank.wallets.application.create.WalletCreatorCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletPutController {

    private final WalletCreator creator;
    private final UuidGenerator uuidGenerator;

    public WalletPutController(WalletCreator creator, UuidGenerator uuidGenerator) {
        this.creator = creator;
        this.uuidGenerator = uuidGenerator;
    }

    @PutMapping("/wallets")
    public ResponseEntity<String> post(@RequestBody PostWalletBody body) {
        final var id = uuidGenerator.generate();
        try {
            creator.create(WalletCreatorCommand.create(id, body.getCustomerId()));
        } catch (WalletNotCreated ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ex.getLocalizedMessage());
        }
        final var responseBody = String.format("Wallet with id %s created", id);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    private static class PostWalletBody {
        private final String customerId;

        // Error jackson serialize(1 arg)
        public PostWalletBody() {
            this.customerId = null;
        }

        public PostWalletBody(String customerId) {
            this.customerId = customerId;
        }

        public String getCustomerId() {
            return customerId;
        }
    }
}
