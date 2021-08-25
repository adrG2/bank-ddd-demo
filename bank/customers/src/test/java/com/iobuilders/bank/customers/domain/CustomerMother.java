package com.iobuilders.bank.customers.domain;

public class CustomerMother {
    public static Customer random() {
        return Customer.create(
                CustomerIdMother.random(),
                CustomerPasswordMother.random(),
                CustomerEmailMother.random(),
                CustomerUsernameMother.random());
    }
}
