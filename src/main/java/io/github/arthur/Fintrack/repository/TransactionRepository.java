package io.github.arthur.Fintrack.repository;

import io.github.arthur.Fintrack.model.Transaction;
import io.github.arthur.Fintrack.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {

    List<Transaction> findByUser(User user);

    Optional<Transaction> findByIdAndUser(UUID id, User user);

    @Modifying
    @Transactional
    void deleteByIdAndUser(UUID id, User user);
}
