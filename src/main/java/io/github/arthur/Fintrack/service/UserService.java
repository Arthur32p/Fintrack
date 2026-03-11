package io.github.arthur.Fintrack.service;

import io.github.arthur.Fintrack.exceptions.RecordNotFoundException;
import io.github.arthur.Fintrack.validator.UserValidator;
import io.github.arthur.Fintrack.model.User;
import io.github.arthur.Fintrack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserValidator validator;
    private final PasswordEncoder passwordEncoder;

    public User create(User user){
        validator.validate(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user);
    }

    public User findById(UUID id){
        return repository.findById(id).orElseThrow(() -> new RecordNotFoundException("Usuário não encontrado"));
    }

    public List<User> findAll(){
        return repository.findAll();
    }

    public User update(UUID id, User user){
        User existing = findById(id);
        user.setId(existing.getId());
        user.setCreatedAt(existing.getCreatedAt());
        validator.validate(user);
        return repository.save(user);
    }

    public void delete(UUID id){
        User user = findById(id);
        repository.delete(user);
    }
}
