package com.iobuilders.bank.customers.application.find;

import com.iobuilders.bank.customers.domain.Customer;
import com.iobuilders.bank.customers.domain.CustomerRepository;
import com.iobuilders.bank.shared.domain.Service;

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
