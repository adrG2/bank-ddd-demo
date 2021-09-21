package com.bank.shared;

import com.bank.shared.domain.Money;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

public final class MoneyTestCase {

    @Test
    public void when_amount_is_negative() {
        final var amount = new BigDecimal("-100");
        final var money = Money.of(amount);
        Assertions.assertEquals(amount, money.amount());
        Assertions.assertEquals("-100", money.amount().toString());
    }
}
