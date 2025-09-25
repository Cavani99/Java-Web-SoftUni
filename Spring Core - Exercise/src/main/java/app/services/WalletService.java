package app.services;

import app.entities.Transaction;
import app.entities.User;
import app.entities.Wallet;

import java.math.BigDecimal;
import java.util.UUID;

public interface WalletService {

    void createNewWallet(User user);

    Wallet getWalletByUser(User user);

    Transaction topUp(UUID walletId, BigDecimal amount);

    Transaction charge(User user, UUID walletId, BigDecimal amount, String chargeDescription);
}
