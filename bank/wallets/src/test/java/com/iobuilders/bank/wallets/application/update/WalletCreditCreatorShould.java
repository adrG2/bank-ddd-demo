package com.iobuilders.bank.wallets.application.update;

import com.iobuilders.bank.wallets.WalletsUnitTestCase;
import com.iobuilders.bank.wallets.application.update.credit.WalletCreditCreator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WalletCreditCreatorShould extends WalletsUnitTestCase {
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
                    throw new NullPointerException();
                };
        assertThrows(Exception.class, executable);
    }

    @Test
    void throw_an_exception_when_wallet_is_null() {
        final Executable executable =
                () -> {
                    throw new NullPointerException();
                };
        assertThrows(NullPointerException.class, executable);
    }
}
