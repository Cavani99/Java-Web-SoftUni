package app.services;

import app.entities.Transaction;
import app.entities.User;
import app.entities.Wallet;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface WalletService {

    void createNewWallet(User user);

    void createDefaultWallet(User user);

    Wallet getActiveWalletByUser(User user);
    List<Wallet> getWalletsByUser(User user);

    Transaction topUp(UUID walletId, BigDecimal amount);

    Transaction charge(User user, UUID walletId, BigDecimal amount, String chargeDescription);
}
