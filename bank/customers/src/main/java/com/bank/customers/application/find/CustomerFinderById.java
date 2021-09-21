package com.bank.customers.application.find;

import com.bank.customers.domain.Customer;
import com.bank.customers.domain.CustomerRepository;
import com.bank.shared.domain.Service;

@Service
public final class CustomerFinderById {

    private final CustomerRepository repository;

    public CustomerFinderById(CustomerRepository repository) {
        this.repository = repository;
    }

    public Customer find(String id) {
        return repository.findOrFailById(id);
    }
}
