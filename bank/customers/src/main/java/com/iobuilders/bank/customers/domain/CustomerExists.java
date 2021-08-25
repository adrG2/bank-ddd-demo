package com.iobuilders.bank.customers.domain;

public class CustomerExists extends RuntimeException {
    public CustomerExists(CustomerId customerId) {
        super(String.format("Customer with id %s already exists", customerId.value()));
    }
}
