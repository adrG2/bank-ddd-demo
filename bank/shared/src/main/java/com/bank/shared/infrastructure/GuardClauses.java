package com.bank.shared.infrastructure;

import org.apache.commons.lang3.StringUtils;

public final class GuardClauses {
    public static void ensureStringIsNotBlank(String s) {
        if (StringUtils.isBlank(s)) {
            throw new IllegalArgumentException();
        }
    }
}
