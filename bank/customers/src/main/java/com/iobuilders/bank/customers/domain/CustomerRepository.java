package com.iobuilders.bank.customers.domain;

public interface CustomerRepository {
    void save(Customer customer);

    Customer findOrFailById(String id);
}
