package io.github.arthur.Fintrack.service;

import io.github.arthur.Fintrack.dto.UserResponseDTO;
import io.github.arthur.Fintrack.model.User;
import io.github.arthur.Fintrack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public User create(User user){
        return repository.save(user);
    }

    public Optional<User> findById(UUID id){
        return repository.findById(id);
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public User update(User user){
        return repository.save(user);
    }
}
