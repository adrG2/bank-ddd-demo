package com.bank.shared.domain;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class Identifier implements Serializable {
    protected final String value;

    public Identifier(String value) {
        Objects.requireNonNull(value, "Identifier should not be null");
        ensureValidUuid(value);

        this.value = value;
    }

    private static void ensureValidUuid(String value) throws IllegalArgumentException {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException ex) {
            throw new UuidNotValid(value, ex);
        }
    }

    public String value() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Identifier that = (Identifier) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
