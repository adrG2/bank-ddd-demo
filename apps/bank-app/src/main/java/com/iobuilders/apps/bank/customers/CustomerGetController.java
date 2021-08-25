package com.iobuilders.apps.bank.customers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public final class CustomerGetController {

    @GetMapping("/customers/{id}")
    public ResponseEntity<Map<String, String>> index(@PathVariable String id) {
        System.out.println(id);
        final var customerResponse = Map.of(
                "id", id,"email", "example@example.com", "userName", "example"
        );
        return ResponseEntity.ok().body(customerResponse);
    }
}
