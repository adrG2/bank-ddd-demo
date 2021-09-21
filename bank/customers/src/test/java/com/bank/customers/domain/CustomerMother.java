package com.bank.customers.domain;

public class CustomerMother {
    public static Customer random() {
        return Customer.create(
                CustomerIdMother.random(),
                CustomerPasswordMother.random(),
                CustomerEmailMother.random(),
                CustomerUsernameMother.random());
    }

    public static Customer withIdBlank() {
        return Customer.create(
                CustomerIdMother.blank(),
                CustomerPasswordMother.random(),
                CustomerEmailMother.random(),
                CustomerUsernameMother.random());
    }

    public static Customer withIdNull() {
        return Customer.create(
                null,
                CustomerPasswordMother.random(),
                CustomerEmailMother.random(),
                CustomerUsernameMother.random());
    }

    public static Customer withIdNotValid() {
        return Customer.create(
                CustomerIdMother.notValidUuid(),
                CustomerPasswordMother.random(),
                CustomerEmailMother.random(),
                CustomerUsernameMother.random());
    }
}
