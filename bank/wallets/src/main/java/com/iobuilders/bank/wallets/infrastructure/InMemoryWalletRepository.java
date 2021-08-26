package com.iobuilders.bank.wallets.infrastructure;

import com.iobuilders.bank.shared.domain.Service;
import com.iobuilders.bank.wallets.domain.Wallet;
import com.iobuilders.bank.wallets.domain.WalletId;
import com.iobuilders.bank.wallets.domain.WalletNotFound;
import com.iobuilders.bank.wallets.domain.WalletRepository;

import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
@Service
public final class InMemoryWalletRepository implements WalletRepository {

    private static final Map<WalletId, Wallet> wallets = new HashMap<>();

    @Override
    public void save(Wallet wallet) {
        ensureWalletIsValid(wallet);
        ensureWalletNotExists(wallet);
        wallets.put(wallet.id(), wallet);
    }

    private void ensureWalletIsValid(Wallet wallet) {
        if (wallet == null) {
            throw new IllegalArgumentException("Wallet must be not null");
        }

        if (wallet.id() == null) {
            throw new IllegalArgumentException("WalletId must be not null");
        }
    }

    private void ensureWalletNotExists(Wallet wallet) {
        final var id = wallet.id();
        if (findById(id.value()) != null) {
            throw new WalletNotFound(id.value());
        }
    }

    @Override
    public Wallet findOrFailById(String id) {
        final var wallet = findById(id);
        ensureWalletExists(id, wallet);
        return wallet;
    }

    private void ensureWalletExists(String id, Wallet wallet) {
        if (wallet == null) {
            throw new WalletNotFound(id);
        }
    }

    private Wallet findById(String id) {
        return wallets.get(new WalletId(id));
    }
}
