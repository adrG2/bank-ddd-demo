package com.iobuilders.bank.customers.domain;

import com.iobuilders.bank.shared.domain.AggregateRoot;

public final class Customer extends AggregateRoot {
    private final CustomerId id;
    private final CustomerEmail email;
    private final CustomerPassword password;
    private final CustomerUserName userName;

    private Customer(
            CustomerId customerId,
            CustomerEmail email,
            CustomerPassword password,
            CustomerUserName userName) {
        this.id = customerId;
        this.email = email;
        this.password = password;
        this.userName = userName;
    }

    public static Customer create(
            String id, String passwordEncoded, String email, String userName) {
        final var customerId = new CustomerId(id);
        final var customerPassword = new CustomerPassword(passwordEncoded);
        final var customerEmail = new CustomerEmail(email);
        final var customerUserName = new CustomerUserName(userName);
        final var customer =
                new Customer(customerId, customerEmail, customerPassword, customerUserName);

        final var event = new CustomerCreated(customerId.value());

        customer.pushEvent(event);

        return customer;
    }

    public CustomerId id() {
        return id;
    }

    public CustomerEmail email() {
        return email;
    }

    public CustomerPassword password() {
        return password;
    }

    public CustomerUserName userName() {
        return userName;
    }

    @Override
    public String toString() {
        return "Customer{"
                + "id="
                + id
                + ", email="
                + email
                + ", password="
                + password
                + ", userName="
                + userName
                + '}';
    }
}
