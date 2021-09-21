package com.bank.customers.domain;

public final class CustomerNotFound extends RuntimeException {
    public CustomerNotFound(String id) {
        super(String.format("The user with id <%s> not exists", id));
    }
}
