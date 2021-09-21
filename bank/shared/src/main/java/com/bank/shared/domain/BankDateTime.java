package com.bank.shared.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class BankDateTime {

    private final String value;
    private static final DateTimeFormatter ISO_FORMAT = DateTimeFormatter.ISO_LOCAL_DATE;

    private BankDateTime(String value) {
        this.value = value;
    }

    public static BankDateTime create(LocalDateTime dateTime) {
        return new BankDateTime(dateTime.toString());
    }

    public static String formatISO(LocalDateTime dateTime) {
        final var bankDateTime = new BankDateTime(dateTime.format(ISO_FORMAT));
        return bankDateTime.value;
    }

    public static String format(LocalDateTime dateTime, DateTimeFormatter formatter) {
        final var bankDateTime = new BankDateTime(dateTime.format(formatter));
        return bankDateTime.value;
    }

    public String value() {
        return value;
    }
}
