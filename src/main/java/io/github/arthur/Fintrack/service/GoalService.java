package io.github.arthur.Fintrack.service;

import io.github.arthur.Fintrack.model.Goal;
import io.github.arthur.Fintrack.model.User;
import io.github.arthur.Fintrack.repository.GoalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository repository;

    public Goal create(Goal goal, User user){
        goal.setUser(user);
        return repository.save(goal);
    }
}
