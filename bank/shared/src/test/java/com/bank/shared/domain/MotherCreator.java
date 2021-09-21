package com.bank.shared.domain;

import com.github.javafaker.Faker;

public final class MotherCreator {
    private final static Faker faker = Faker.instance();

    public static Faker random() {
        return faker;
    }
}
