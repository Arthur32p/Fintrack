package io.github.arthur.Fintrack.repository;

import io.github.arthur.Fintrack.model.Goal;
import io.github.arthur.Fintrack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface GoalRepository extends JpaRepository<Goal, UUID> {

    List<Goal> findAllByUser(User user);
}
