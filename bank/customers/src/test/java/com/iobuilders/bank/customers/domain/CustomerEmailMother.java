package com.iobuilders.bank.customers.domain;

import com.iobuilders.bank.shared.domain.MotherCreator;

public class CustomerEmailMother {
    public static String random() {
        return MotherCreator.random().internet().emailAddress();
    }
}
