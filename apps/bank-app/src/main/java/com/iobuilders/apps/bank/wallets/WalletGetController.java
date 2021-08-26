package com.iobuilders.apps.bank.wallets;

import com.iobuilders.bank.wallets.application.find.WalletFinderById;
import com.iobuilders.bank.wallets.domain.Wallet;
import com.iobuilders.bank.wallets.domain.WalletNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

@RestController
public class WalletGetController {

    private final WalletFinderById finder;

    public WalletGetController(WalletFinderById finder) {
        this.finder = finder;
    }

    @GetMapping("/wallets/{id}")
    public ResponseEntity<Map<String, Serializable>> get(@PathVariable String id) {
        try {
            ensureIdNotBlank(id);
            var wallet = finder.find(id);
            return ResponseEntity.ok().body(buildResponse(wallet));
        } catch (WalletNotFound notFound) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException badRequest) {
            return ResponseEntity.badRequest().build();
        }
    }

    private void ensureIdNotBlank(String id) {
        if (!StringUtils.hasText(id)) {
            throw new IllegalArgumentException("Id must be not null");
        }
    }

    private Map<String, Serializable> buildResponse(Wallet wallet) {
        return Map.of(
                "id",
                wallet.id().value(),
                "customerId",
                wallet.customerId().value(),
                "money",
                wallet.money());
    }
}
