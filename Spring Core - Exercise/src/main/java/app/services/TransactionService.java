package app.services;

import app.entities.Transaction;
import app.entities.User;

import java.util.List;

public interface TransactionService {

    List<Transaction> getTransactionByReceiver(String receiver);
    List<Transaction> getTransactionByOwner(User owner);
}
