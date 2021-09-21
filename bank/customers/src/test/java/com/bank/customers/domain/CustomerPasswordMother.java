package com.bank.customers.domain;

import com.bank.shared.domain.MotherCreator;

public class CustomerPasswordMother {
    public static String random() {
        final var faker = MotherCreator.random();
        final var fakerCrypto = faker.crypto();
        return fakerCrypto.md5();
    }
}
