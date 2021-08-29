package com.iobuilders.bank.wallets;

import com.iobuilders.bank.shared.infrastructure.UnitTestCase;
import com.iobuilders.bank.wallets.domain.Wallet;
import com.iobuilders.bank.wallets.domain.WalletExists;
import com.iobuilders.bank.wallets.domain.WalletRepository;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class WalletsUnitTestCase extends UnitTestCase {
    protected WalletRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        repository = mock(WalletRepository.class);
    }

    public void shouldHaveSaved(Wallet wallet) {
        Mockito.doNothing().when(repository).save(wallet);
    }

    public void shouldNotHaveSaved(Wallet wallet) {
        Mockito.doThrow(new Exception()).when(repository).save(wallet);
    }

    public void shouldThrowWalletExists(Wallet wallet) {
        Mockito.doThrow(new WalletExists(wallet.id().value()))
                .when(repository)
                .save(Mockito.any(Wallet.class));
    }
}
