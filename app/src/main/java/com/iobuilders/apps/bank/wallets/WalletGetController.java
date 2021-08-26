package com.iobuilders.apps.bank.wallets;

import com.iobuilders.apps.bank.customers.CustomerGetController;
import com.iobuilders.bank.shared.infrastructure.GuardClauses;
import com.iobuilders.bank.wallets.application.find.WalletFinderById;
import com.iobuilders.bank.wallets.domain.WalletNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WalletGetController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerGetController.class);

    private final WalletFinderById finder;

    public WalletGetController(WalletFinderById finder) {
        this.finder = finder;
    }

    @GetMapping("/wallets/{id}")
    public ResponseEntity<WalletResponse> get(@PathVariable String id) {
        try {
            GuardClauses.ensureStringIsNotBlank(id);
            var wallet = finder.find(id);
            return ResponseEntity.ok().body(WalletResponse.from(wallet));
        } catch (WalletNotFound notFound) {
            logger.error(notFound.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException badRequest) {
            logger.error(badRequest.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
