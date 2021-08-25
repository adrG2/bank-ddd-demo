package com.iobuilders.bank.customers.infrastructure;

import com.iobuilders.bank.customers.domain.Customer;
import com.iobuilders.bank.customers.domain.CustomerExists;
import com.iobuilders.bank.customers.domain.CustomerId;
import com.iobuilders.bank.customers.domain.CustomerRepository;
import org.springframework.stereotype.Service;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Service
public final class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<CustomerId, Customer> customers = new HashMap<>();

    @Override
    public void save(Customer customer) {
        ensureCustomerIsValid(customer);
        ensureCustomerNotExists(customer);
        customers.put(customer.getId(), customer);
    }

    private void ensureCustomerIsValid(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer must not be null");
        }

        if (customer.getId() == null) {
            throw new IllegalArgumentException("CustomerId must not be null");
        }
    }

    private void ensureCustomerNotExists(Customer customer) {
        final var id = customer.getId();
        if (findById(id) != null) {
            throw new CustomerExists(id);
        }
    }

    @Override
    public Customer findOrFailById(String id) {
        return findById(new CustomerId(id));
    }

    private Customer findById(CustomerId id) {
        return customers.get(id);
    }
}
