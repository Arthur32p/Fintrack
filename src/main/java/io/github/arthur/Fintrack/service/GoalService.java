package io.github.arthur.Fintrack.service;

import io.github.arthur.Fintrack.exceptions.RecordNotFoundException;
import io.github.arthur.Fintrack.model.Goal;
import io.github.arthur.Fintrack.model.User;
import io.github.arthur.Fintrack.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository repository;

    public Goal create(Goal goal, User user){
        goal.setUser(user);
        return repository.save(goal);
    }

    public List<Goal> getAll(User user){
        return repository.findAllByUser(user);
    }

    public Goal findById(UUID id, User user){
        return repository.findByIdAndUser(id, user).orElseThrow(() -> new RecordNotFoundException("Meta inexistente"));
    }

    public Goal update(UUID id, Goal goal, User user){
        Goal existing = repository.findByIdAndUser(id, user).orElseThrow(() -> new RecordNotFoundException("Meta inexistente"));
        goal.setId(existing.getId());
        goal.setUser(existing.getUser());
        return repository.save(goal);
    }
}
