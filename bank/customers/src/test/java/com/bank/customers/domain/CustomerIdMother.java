package com.bank.customers.domain;

import com.bank.shared.domain.UuidMother;

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
