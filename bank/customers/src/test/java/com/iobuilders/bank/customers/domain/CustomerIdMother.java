package com.iobuilders.bank.customers.domain;

import com.iobuilders.bank.shared.domain.UuidMother;

public class CustomerIdMother {
    public static String random() {
        return UuidMother.random();
    }
}
