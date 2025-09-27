package app.services.impl;

import app.entities.Transaction;
import app.entities.User;
import app.entities.Wallet;
import app.entities.enums.TransactionStatus;
import app.entities.enums.TransactionType;
import app.entities.enums.WalletStatus;
import app.repositories.TransactionRepo;
import app.repositories.WalletRepo;
import app.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class WalletServiceImpl implements WalletService {

    private WalletRepo walletRepo;
    private TransactionRepo transactionRepo;

    @Autowired
    public WalletServiceImpl(WalletRepo walletRepo, TransactionRepo transactionRepo) {
        this.walletRepo = walletRepo;
        this.transactionRepo = transactionRepo;
    }

    @Override
    public void createNewWallet(User user) {
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(20));
        wallet.setStatus(WalletStatus.ACTIVE);
        wallet.setCurrency("EUR");
        wallet.setUser(user);
        wallet.setCreatedOn(LocalDateTime.now());
        wallet.setUpdatedOn(LocalDateTime.now());

        walletRepo.save(wallet);
    }

    @Override
    public Wallet getActiveWalletByUser(User user) {
        Optional<Wallet> wallet = walletRepo.findByOwnerAndStatus(user, WalletStatus.ACTIVE);

        return wallet.orElse(null);
    }

    @Override
    public List<Wallet> getWalletsByUser(User user) {
        return walletRepo.findByOwner(user);
    }

    @Override
    public Transaction topUp(UUID walletId, BigDecimal amount) {
        Optional<Wallet> currentWallet = walletRepo.findById(walletId);

        if (currentWallet.isPresent() && currentWallet.get().getStatus().equals(WalletStatus.ACTIVE)) {
            Wallet wallet = currentWallet.get();

            wallet.setBalance(wallet.getBalance().add(amount));
            wallet.setUpdatedOn(LocalDateTime.now());

            walletRepo.save(wallet);

            Transaction transaction = new Transaction();
            transaction.setAmount(amount);
            transaction.setStatus(TransactionStatus.SUCCEEDED);
            transaction.setCurrency("EUR");
            transaction.setType(TransactionType.DEPOSIT);
            transaction.setSender(wallet.getUser().getUsername());
            transaction.setReceiver(wallet.getUser().getUsername());
            transaction.setBalanceLeft(wallet.getBalance());
            transaction.setUser(wallet.getUser());
            transaction.setCreatedOn(LocalDateTime.now());

            transactionRepo.save(transaction);

            return transaction;
        }

        return null;
    }

    @Override
    public Transaction charge(User user, UUID walletId, BigDecimal amount, String chargeDescription) {
        Optional<Wallet> currentWallet = walletRepo.findById(walletId);
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setCurrency("EUR");
        transaction.setType(TransactionType.WITHDRAWAL);
        transaction.setReceiver(user.getUsername());
        transaction.setUser(user);
        transaction.setCreatedOn(LocalDateTime.now());

        if (currentWallet.isPresent() && currentWallet.get().getStatus().equals(WalletStatus.ACTIVE)) {
            Wallet wallet = currentWallet.get();

            if (wallet.getBalance().compareTo(amount) < 0) {
                transaction.setStatus(TransactionStatus.FAILED);
                transaction.setSender(user.getUsername());
                transaction.setDescription("Wallet does not have sufficient funds!");

                transactionRepo.save(transaction);

                return transaction;
            }

            wallet.setBalance(wallet.getBalance().subtract(amount));
            wallet.setUpdatedOn(LocalDateTime.now());
            walletRepo.save(wallet);

            transaction.setStatus(TransactionStatus.SUCCEEDED);
            transaction.setSender(user.getUsername());
            transaction.setDescription(chargeDescription);

        } else {
            transaction.setStatus(TransactionStatus.FAILED);
            transaction.setSender(user.getUsername());
            transaction.setDescription("Wallet does not exist or is not active!");

        }

        transactionRepo.save(transaction);
        return transaction;

    }
}
