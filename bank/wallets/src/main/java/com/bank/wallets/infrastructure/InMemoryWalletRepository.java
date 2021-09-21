package com.bank.wallets.infrastructure;

import com.bank.shared.domain.Service;
import com.bank.wallets.domain.Wallet;
import com.bank.wallets.domain.WalletNotFound;
import com.bank.wallets.domain.WalletRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Service
public final class InMemoryWalletRepository implements WalletRepository {

    private static final Map<String, Wallet> wallets = new HashMap<>();

    @Override
    public void save(Wallet wallet) {
        ensureWalletIsValid(wallet);
        wallets.put(wallet.id().value(), wallet);
    }

    private void ensureWalletIsValid(Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet must be not null");
        }

        if (wallet.id() == null) {
            throw new IllegalArgumentException("WalletId must be not null");
        }
    }

    @Override
    public Wallet findOrFailById(String id) {
        final var wallet = findById(id);
        ensureWalletExists(id, wallet);
        return wallet;
    }

    private Wallet findById(String id) {
        return wallets.get(id);
    }

    private void ensureWalletExists(String id, Wallet wallet) {
        if (wallet == null) {
            throw new WalletNotFound(id);
        }
    }
}
