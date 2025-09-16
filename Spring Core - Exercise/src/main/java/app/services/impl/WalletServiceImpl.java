package app.services.impl;

import app.entities.User;
import app.entities.Wallet;
import app.entities.enums.WalletStatus;
import app.repositories.WalletRepo;
import app.services.WalletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class WalletServiceImpl implements WalletService {

    private WalletRepo walletRepo;

    @Autowired
    public WalletServiceImpl(WalletRepo walletRepo) {
        this.walletRepo = walletRepo;
    }

    @Override
    public void createNewWaller(User user) {
        Wallet wallet = new Wallet();
        wallet.setBalance(BigDecimal.valueOf(20));
        wallet.setStatus(WalletStatus.ACTIVE);
        wallet.setCurrency("EUR");
        wallet.setUser(user);
        wallet.setCreatedOn(LocalDateTime.now());
        wallet.setUpdatedOn(LocalDateTime.now());

        walletRepo.save(wallet);
    }
}
