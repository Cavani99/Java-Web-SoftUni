package app.repositories;

import app.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    List<Transaction> findByReceiver(String receiver);
    List<Transaction> findBySender(String sender);
}
