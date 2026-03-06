package io.github.arthur.Fintrack.repository;

import io.github.arthur.Fintrack.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
}
