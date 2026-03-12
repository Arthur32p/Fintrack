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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

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
}
