package io.github.arthur.Fintrack.service;

import io.github.arthur.Fintrack.exceptions.RecordNotFoundException;
import io.github.arthur.Fintrack.model.Transaction;
import io.github.arthur.Fintrack.model.User;
import io.github.arthur.Fintrack.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

    public Transaction findById(UUID id, User user){
        return repository.findByIdAndUser(id, user).orElseThrow(() -> new RecordNotFoundException("Transação inexistente"));
    }

    public void delete(UUID id, User user){
        Transaction transaction = repository.findByIdAndUser(id, user)
                .orElseThrow(() -> new RecordNotFoundException("Transação inexistente"));
        repository.delete(transaction);
    }

    public Transaction update(UUID id, Transaction transaction, User user){
        Transaction existing = repository.findByIdAndUser(id, user).orElseThrow(() -> new RecordNotFoundException("Transação inexistente"));
        transaction.setId(existing.getId());
        transaction.setUser(existing.getUser());
        return repository.save(transaction);
    }
}
