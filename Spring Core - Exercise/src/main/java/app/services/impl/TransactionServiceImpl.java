package app.services.impl;

import app.entities.Transaction;
import app.entities.User;
import app.repositories.TransactionRepo;
import app.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepo transactionRepo;

    @Autowired
    public TransactionServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public List<Transaction> getTransactionByReceiver(String receiver) {
        return transactionRepo.findByReceiver(receiver);
    }

    @Override
    public List<Transaction> getTransactionByOwner(User owner) {
        return transactionRepo.findByOwner(owner);
    }
}
