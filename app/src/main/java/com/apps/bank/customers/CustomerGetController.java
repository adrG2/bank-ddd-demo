package com.apps.bank.customers;

import com.bank.customers.application.find.CustomerFinderById;
import com.bank.customers.domain.CustomerNotFound;
import com.bank.shared.infrastructure.GuardClauses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class CustomerGetController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerGetController.class);
    private final CustomerFinderById finder;

    public CustomerGetController(CustomerFinderById finder) {
        this.finder = finder;
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<CustomerResponse> get(@PathVariable String id) {
        try {
            GuardClauses.ensureStringIsNotBlank(id);
            var customer = finder.find(id);
            final var response = CustomerResponse.from(customer);
            return ResponseEntity.ok().body(response);
        } catch (CustomerNotFound notFound) {
            logger.error(notFound.getMessage());
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException badRequest) {
            logger.error(badRequest.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
