package io.github.arthur.Fintrack.validator;

import io.github.arthur.Fintrack.exceptions.DuplicateRecordException;
import io.github.arthur.Fintrack.model.User;
import io.github.arthur.Fintrack.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository repository;

    public void validate(User user){
        if(existsUser(user)){
            throw new DuplicateRecordException("Autor já cadastrado");
        }
    }

    private boolean existsUser(User user){
        Optional<User> userFound = repository.findByEmail(user.getEmail());

        if(user.getId() == null){
            return userFound.isPresent();
        }

        return userFound.isPresent() && !user.getId().equals(userFound.get().getId());
    }
}
