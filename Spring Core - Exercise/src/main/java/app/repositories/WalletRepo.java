package app.repositories;

import app.entities.User;
import app.entities.Wallet;
import app.entities.enums.WalletStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WalletRepo extends JpaRepository<Wallet, Long> {
    Optional<Wallet> findById(UUID id);

    Optional<Wallet> findByOwnerAndStatus(User owner, WalletStatus walletStatus);

    List<Wallet> findByOwnerOrderByCreatedOn(User owner);
}
