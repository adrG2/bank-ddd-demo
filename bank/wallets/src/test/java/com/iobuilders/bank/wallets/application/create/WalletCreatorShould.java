package com.iobuilders.bank.wallets.application.create;

import com.iobuilders.bank.shared.domain.UuidNotValid;
import com.iobuilders.bank.wallets.WalletsUnitTestCase;
import com.iobuilders.bank.wallets.domain.WalletExists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WalletCreatorShould extends WalletsUnitTestCase {
    private WalletCreator creator;

    @BeforeEach
    @Override
    protected void setUp() {
        super.setUp();

        creator = new WalletCreator(eventBus, repository);
    }

    @Test
    void create_valid_wallet() {}

    @Test
    void throw_an_exception_when_repository_is_down() {
        final Executable executable =
                () -> {
                    throw new Exception();
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

    @Test
    void throw_an_exception_when_wallet_id_is_null() {
        final Executable executable =
                () -> {
                    throw new NullPointerException();
                };

        assertThrows(NullPointerException.class, executable);
    }

    @Test
    void throw_an_exception_when_wallet_id_not_valid_uuid() {
        final Executable executable =
                () -> {
                    throw new UuidNotValid("1");
                };

        assertThrows(UuidNotValid.class, executable);
    }

    @Test
    void throw_an_exception_when_wallet_exists() {
        final Executable executable =
                () -> {
                    throw new WalletExists("");
                };

        assertThrows(WalletExists.class, executable);
    }
}
