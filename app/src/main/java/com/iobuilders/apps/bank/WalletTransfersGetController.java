package com.iobuilders.apps.bank;

import com.iobuilders.bank.shared.infrastructure.GuardClauses;
import com.iobuilders.bank.transfers.domain.Transfer;
import com.iobuilders.bank.wallets.domain.WalletNotFound;
import com.iobuilders.bank.wallets_transfers.application.WalletTransfersFinder;
import com.iobuilders.bank.wallets_transfers.domain.TransfersNotFount;
import com.iobuilders.bank.wallets_transfers.domain.WalletTransfers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public final class WalletTransfersGetController {

    private static final Logger logger =
            LoggerFactory.getLogger(WalletTransfersGetController.class);
    private final WalletTransfersFinder finder;

    public WalletTransfersGetController(WalletTransfersFinder finder) {
        this.finder = finder;
    }

    @GetMapping("/wallet/{walletId}/transfers")
    public ResponseEntity<WalletTransfersResponse> get(@PathVariable String walletId) {
        try {
            logger.debug("Get all transfers to walletId {}", walletId);
            GuardClauses.ensureStringIsNotBlank(walletId);
            final var walletTransfers = finder.find(walletId);
            return ResponseEntity.ok().body(new WalletTransfersResponse(walletTransfers));
        } catch (WalletNotFound | TransfersNotFount notFound) {
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException badRequest) {
            return ResponseEntity.badRequest().build();
        }
    }

    private static class WalletTransfersResponse {

        private final String walletId;
        private final String customerId;
        private final String balance;
        private final List<Transfer> transfers;

        public WalletTransfersResponse(WalletTransfers walletTransfers) {
            this.walletId = walletTransfers.getWallet().id().value();
            this.customerId = walletTransfers.getWallet().customerId().value();
            this.balance = walletTransfers.getWallet().money().amountAsString();
            this.transfers = walletTransfers.getTransfers();
        }

        public String getWalletId() {
            return walletId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public String getBalance() {
            return balance;
        }

        public List<Transfer> getTransfers() {
            return transfers;
        }

        @Override
        public String toString() {
            return "WalletTransfersResponse{"
                    + "walletId='"
                    + walletId
                    + '\''
                    + ", customerId='"
                    + customerId
                    + '\''
                    + ", balance='"
                    + balance
                    + '\''
                    + ", transfers="
                    + transfers
                    + '}';
        }
    }
}
