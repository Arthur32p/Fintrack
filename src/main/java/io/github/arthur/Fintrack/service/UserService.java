package io.github.arthur.Fintrack.service;

import io.github.arthur.Fintrack.model.User;
import io.github.arthur.Fintrack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User create(User user){
        return repository.save(user);
    }
}
