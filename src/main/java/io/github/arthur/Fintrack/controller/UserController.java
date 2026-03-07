package io.github.arthur.Fintrack.controller;

import io.github.arthur.Fintrack.dto.UserRequestDTO;
import io.github.arthur.Fintrack.dto.UserResponseDTO;
import io.github.arthur.Fintrack.mapper.UserMapper;
import io.github.arthur.Fintrack.model.User;
import io.github.arthur.Fintrack.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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
        return service.findById(id)
                .map(user -> {
                    var response = mapper.toResponse(user);
                    return ResponseEntity.ok(response);
                }).orElseGet(null);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        List<User> users = service.findAll();
        List<UserResponseDTO> list = users.stream().map(mapper::toResponse).toList();

        return ResponseEntity.ok(list);
    }

}
