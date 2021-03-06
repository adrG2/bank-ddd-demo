package com.bank.customers.infrastructure;

import com.bank.customers.domain.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Service
public final class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<String, Customer> customers = new HashMap<>();

    @Override
    public void save(Customer customer) {
        ensureCustomerIsValid(customer);
        ensureCustomerNotExists(customer);
        customers.put(customer.id().value(), customer);
    }

    private void ensureCustomerIsValid(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer should not be null");
        }

        final var id = customer.id();
        if (id == null || StringUtils.isBlank(id.value())) {
            throw new IllegalArgumentException("CustomerId should not be null");
        }
    }

    private void ensureCustomerNotExists(Customer customer) {
        final var id = customer.id();
        if (findById(id) != null) {
            throw new CustomerExists(id.value());
        }
    }

    @Override
    public Customer findOrFailById(String id) {
        final var customer = findById(new CustomerId(id));
        ensureCustomerExists(id, customer);
        return customer;
    }

    private void ensureCustomerExists(String id, Customer customer) {
        if (customer == null) {
            throw new CustomerNotFound(id);
        }
    }

    private Customer findById(CustomerId id) {
        return customers.get(id.value());
    }
}
