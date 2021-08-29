package com.iobuilders.apps.bank.wallets;

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
import java.util.stream.Collectors;

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
            GuardClauses.ensureStringIsNotBlank(walletId);
            final var walletTransfers = finder.find(walletId);
            return ResponseEntity.ok().body(WalletTransfersResponse.from(walletTransfers));
        } catch (WalletNotFound | TransfersNotFount notFound) {
            logger.error(notFound.getMessage());
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException badRequest) {
            logger.error(badRequest.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    private static class WalletTransfersResponse {

        private final String walletId;
        private final String customerId;
        private final String balance;
        private final List<TransferResponse> transfers;

        private WalletTransfersResponse(
                String walletId,
                String customerId,
                String balance,
                List<TransferResponse> transfers) {
            this.walletId = walletId;
            this.customerId = customerId;
            this.balance = balance;
            this.transfers = transfers;
        }

        public static WalletTransfersResponse from(WalletTransfers walletTransfers) {
            final var walletId = walletTransfers.getWallet().id().value();
            final var customerId = walletTransfers.getWallet().customerId().value();
            final var balance = walletTransfers.getWallet().balance().amountAsString();
            final var transfers =
                    walletTransfers.getTransfers().stream()
                            .map(TransferResponse::from)
                            .collect(Collectors.toList());
            return new WalletTransfersResponse(walletId, customerId, balance, transfers);
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

        public List<TransferResponse> getTransfers() {
            return transfers;
        }
    }

    private static class TransferResponse {

        private final String transferId;
        private final String walletId;
        private final String money;

        private TransferResponse(String transferId, String walletId, String money) {
            this.transferId = transferId;
            this.walletId = walletId;
            this.money = money;
        }

        public static TransferResponse from(Transfer transfer) {
            return new TransferResponse(
                    transfer.id().value(),
                    transfer.walletId().value(),
                    transfer.money().amountAsString());
        }

        public String getTransferId() {
            return transferId;
        }

        public String getWalletId() {
            return walletId;
        }

        public String getMoney() {
            return money;
        }
    }
}
