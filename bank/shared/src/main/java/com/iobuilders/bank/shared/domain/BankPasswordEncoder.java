package com.iobuilders.bank.shared.domain;

public interface BankPasswordEncoder {
    String encode(String text);

    Boolean matches(String text, String hash);
}
