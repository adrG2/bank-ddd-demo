package com.bank.shared.domain;

public final class UuidNotValid extends RuntimeException {

    public UuidNotValid(String id) {
        super(String.format("Id %s is not valid uuid value", id));
    }

    public UuidNotValid(String id, IllegalArgumentException ex) {
        super(String.format("Id %s is not valid uuid value", id), ex);
    }
}
