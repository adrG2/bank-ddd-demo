package com.iobuilders.bank.customers.application.create;

import com.iobuilders.bank.customers.domain.Customer;

import java.util.Objects;

public final class CustomerCreatorCommand {
    private final String id;
    private final String email;
    private final String userName;
    private final String password;

    private CustomerCreatorCommand(String id, String email, String userName, String password) {
        this.id = id;
        this.email = email;
        this.userName = userName;
        this.password = password;
    }

    public static CustomerCreatorCommand create(
            String id, String email, String userName, String password) {
        return new CustomerCreatorCommand(id, email, userName, password);
    }

    public static CustomerCreatorCommand from(Customer customer) {
        Objects.requireNonNull(customer, "Customer should not be null");
        final var id = customer.id();
        final var email = customer.email();
        final var userName = customer.userName();
        final var password = customer.password();
        return CustomerCreatorCommand.create(
                id.value(), email.value(), userName.value(), password.value());
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
