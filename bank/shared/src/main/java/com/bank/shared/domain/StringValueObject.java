package com.bank.shared.domain;

import java.util.Objects;

public abstract class StringValueObject {
    private final String value;

    public StringValueObject(String value) {
        this.value = value;
    }

    public String value() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StringValueObject)) {
            return false;
        }
        StringValueObject that = (StringValueObject) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
