package io.github.arthur.Fintrack.repository;

import io.github.arthur.Fintrack.model.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GoalRepository extends JpaRepository<Goal, UUID> {
}
