package com.bank.customers.domain;

import com.bank.shared.domain.MotherCreator;

public class CustomerEmailMother {
    public static String random() {
        return MotherCreator.random().internet().emailAddress();
    }
}
