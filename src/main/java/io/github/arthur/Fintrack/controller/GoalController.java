package io.github.arthur.Fintrack.controller;

import io.github.arthur.Fintrack.dto.GoalRequestDTO;
import io.github.arthur.Fintrack.dto.GoalResponseDTO;
import io.github.arthur.Fintrack.mapper.GoalMapper;
import io.github.arthur.Fintrack.model.Goal;
import io.github.arthur.Fintrack.model.User;
import io.github.arthur.Fintrack.service.GoalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("goal")
@RequiredArgsConstructor
public class GoalController implements GenericController{

    private final GoalService service;
    private final GoalMapper mapper;

    @PostMapping
    public ResponseEntity<GoalResponseDTO> create(@RequestBody @Valid GoalRequestDTO dto, @AuthenticationPrincipal User user){
        Goal goal = mapper.toEntity(dto);
        Goal saved = service.create(goal, user);
        GoalResponseDTO response = mapper.toResponse(saved);
        URI location = gerarHeaderLocation(saved.getId());

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<GoalResponseDTO>> getAll(@AuthenticationPrincipal User user){
        List<Goal> goals = service.getAll(user);
        List<GoalResponseDTO> list = goals.stream().map(mapper::toResponse).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<GoalResponseDTO> findById(@PathVariable("id") UUID id, @AuthenticationPrincipal User user){
        Goal goal = service.findById(id, user);
        GoalResponseDTO response = mapper.toResponse(goal);

        return ResponseEntity.ok(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<GoalResponseDTO> update(@PathVariable("id") UUID id, @RequestBody @Valid GoalRequestDTO dto, @AuthenticationPrincipal User user){
        Goal saved = service.update(id, mapper.toEntity(dto), user);
        GoalResponseDTO response = mapper.toResponse(saved);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") UUID id, @AuthenticationPrincipal User user){
        service.delete(id, user);

        return ResponseEntity.noContent().build();
    }
}
