package com.iobuilders.apps.bank.wallets;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class WalletGetController {

    @GetMapping("/wallets/{id}")
    public ResponseEntity<Map<String, String>> get(@PathVariable String id) {
        final var walletResponse = Map.of("id", id);
        return ResponseEntity.ok().body(walletResponse);
    }
}
