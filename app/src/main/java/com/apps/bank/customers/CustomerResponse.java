package com.apps.bank.customers;

import com.bank.customers.domain.Customer;

public class CustomerResponse {
    private final String id;
    private final String userName;
    private final String email;

    public CustomerResponse(String id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public static CustomerResponse from(Customer customer) {
        return new CustomerResponse(
                customer.id().value(), customer.userName().value(), customer.email().value());
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "CustomerResponse{"
                + "id='"
                + id
                + '\''
                + ", userName='"
                + userName
                + '\''
                + ", email='"
                + email
                + '\''
                + '}';
    }
}
