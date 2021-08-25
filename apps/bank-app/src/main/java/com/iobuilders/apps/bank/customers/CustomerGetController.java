package com.iobuilders.apps.bank.customers;

import com.iobuilders.bank.customers.application.find.CustomerFinderById;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.Map;

@RestController
public final class CustomerGetController {

    private final CustomerFinderById finder;

    public CustomerGetController(CustomerFinderById finder) {
        this.finder = finder;
    }

    @GetMapping("/customers/{id}")
    public ResponseEntity<Map<String, Serializable>> get(@PathVariable String id) {
        final var customer = finder.find(id);
        final var customerResponse =
                Map.of(
                        "id",
                        customer.getId(),
                        "email",
                        customer.getEmail().value(),
                        "userName",
                        customer.getUserName().value());
        return ResponseEntity.ok().body(customerResponse);
    }
}
