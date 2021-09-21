package com.bank.customers.domain;

public class CustomerExists extends RuntimeException {
    public CustomerExists(String id) {
        super(String.format("Customer with id %s already exists", id));
    }
}
