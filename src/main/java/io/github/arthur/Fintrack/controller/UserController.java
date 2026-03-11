package io.github.arthur.Fintrack.controller;

import io.github.arthur.Fintrack.dto.UserRequestDTO;
import io.github.arthur.Fintrack.dto.UserResponseDTO;
import io.github.arthur.Fintrack.mapper.UserMapper;
import io.github.arthur.Fintrack.model.User;
import io.github.arthur.Fintrack.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController implements GenericController{

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@RequestBody @Valid UserRequestDTO dto){
        User user = mapper.toEntity(dto);
        User saved = service.create(user);
        UserResponseDTO savedUser = mapper.toResponse(saved);
        URI location = gerarHeaderLocation(saved.getId());

        return ResponseEntity.created(location).body(savedUser);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable("id") UUID id){
        User user = service.findById(id);
        UserResponseDTO userFound = mapper.toResponse(user);

        return ResponseEntity.ok(userFound);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        List<User> users = service.findAll();
        List<UserResponseDTO> list = users.stream().map(mapper::toResponse).toList();

        return ResponseEntity.ok(list);
    }

    @PutMapping("{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable("id") UUID id, @RequestBody @Valid UserRequestDTO dto){
        User saved = service.update(id, mapper.toEntity(dto));
        UserResponseDTO savedUser = mapper.toResponse(saved);

        return ResponseEntity.ok(savedUser);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id){

        service.delete(id);

        return ResponseEntity.noContent().build();
    }

}
