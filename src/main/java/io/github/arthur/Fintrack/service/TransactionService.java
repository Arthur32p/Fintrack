package io.github.arthur.Fintrack.service;

import io.github.arthur.Fintrack.model.Transaction;
import io.github.arthur.Fintrack.model.User;
import io.github.arthur.Fintrack.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository repository;

    public Transaction create(Transaction transaction, User user){
        transaction.setUser(user);
        return repository.save(transaction);
    }

    public List<Transaction> getAll(User user){
        return repository.findByUser(user);
    }
}
