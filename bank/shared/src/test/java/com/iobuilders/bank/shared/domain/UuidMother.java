package com.iobuilders.bank.shared.domain;

import java.util.UUID;

public final class UuidMother {
    public static String random() {
        return UUID.randomUUID().toString();
    }

    public static String notValid() {
        return "1234-1234";
    }
}
