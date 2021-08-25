package com.iobuilders.apps.bank.customers;

import com.iobuilders.bank.customers.application.find.CustomerFinderById;
import com.iobuilders.bank.customers.domain.Customer;
import com.iobuilders.bank.customers.domain.CustomerNotFound;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
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
        try {
            ensureIdNotBlank(id);
            var customer = finder.find(id);
            return ResponseEntity.ok().body(buildResponse(customer));
        } catch (CustomerNotFound notFound) {
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

    private Map<String, Serializable> buildResponse(Customer customer) {
        return Map.of(
                "id",
                customer.id(),
                "email",
                customer.email().value(),
                "userName",
                customer.userName().value());
    }
}
