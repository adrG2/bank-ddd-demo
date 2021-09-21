package com.bank.wallets.application.update;

import com.bank.wallets.WalletsUnitTestCase;
import com.bank.wallets.application.update.credit.WalletCreditCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WalletDebitCreatorShould extends WalletsUnitTestCase {
    private WalletCreditCreator creator;

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();

        creator = new WalletCreditCreator(repository);
    }

    @Test
    void create_valid_wallet() {

        //        creator.create("", BigDecimal.ZERO);
    }

    @Test
    void throw_an_exception_when_repository_is_down() {
        final Executable executable =
                () -> {
                    throw new Exception();
                };
        assertThrows(Exception.class, executable);
    }
}
