package com.iobuilders.bank.customers.application.create;

import com.iobuilders.bank.customers.domain.Customer;

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
        final var id = customer.getId();
        final var email = customer.getEmail();
        final var userName = customer.getUserName();
        final var password = customer.getPassword();
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
