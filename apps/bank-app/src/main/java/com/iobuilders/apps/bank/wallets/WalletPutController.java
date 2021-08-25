package com.iobuilders.apps.bank.wallets;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class WalletPutController {

    @PutMapping("/wallets")
    public ResponseEntity<String> post(@RequestBody PostWalletBody body) {
        final var id = UUID.randomUUID().toString();
        final var responseBody = String.format("Wallet with id %s created", id);
        return new ResponseEntity<>(responseBody, HttpStatus.CREATED);
    }

    private static class PostWalletBody {
        private final String userId;

        // Error jackson serialize(1 arg)
        public PostWalletBody() {
            this.userId = null;
        }

        public PostWalletBody(String userId) {
            this.userId = userId;
        }

        public String getUserId() {
            return userId;
        }
    }
}
