package com.iobuilders.bank.shared.domain;

import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class Identifier implements Serializable {
    protected final String value;

    public Identifier(String value) {
        Objects.requireNonNull(value, "Identifier value must not be null");
        ensureValidUuid(value);

        this.value = value;
    }

    private static void ensureValidUuid(String value) throws IllegalArgumentException {
        final var uuid = UUID.fromString(value);
        Assert.notNull(uuid, "UUID null value");
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
