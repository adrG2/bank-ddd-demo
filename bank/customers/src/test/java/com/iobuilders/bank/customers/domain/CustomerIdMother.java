package com.iobuilders.bank.customers.domain;

import com.iobuilders.bank.shared.domain.UuidMother;

public class CustomerIdMother {
    public static String random() {
        return UuidMother.random();
    }

    public static String blank() {
        return "";
    }

    public static String notValidUuid() {
        return UuidMother.notValid();
    }
}
