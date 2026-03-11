package io.github.arthur.Fintrack.controller;

import io.github.arthur.Fintrack.dto.TransactionRequestDTO;
import io.github.arthur.Fintrack.dto.TransactionResponseDTO;
import io.github.arthur.Fintrack.mapper.TransactionMapper;
import io.github.arthur.Fintrack.model.Transaction;
import io.github.arthur.Fintrack.model.User;
import io.github.arthur.Fintrack.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("transaction")
@RequiredArgsConstructor
public class TransactionController implements GenericController{

    private final TransactionService service;
    private final TransactionMapper mapper;

    @PostMapping
    public ResponseEntity<TransactionResponseDTO> create(@RequestBody @Valid TransactionRequestDTO dto, @AuthenticationPrincipal User user){
        Transaction transaction = mapper.toEntity(dto);
        Transaction saved = service.create(transaction, user);
        TransactionResponseDTO response = mapper.toResponse(saved);
        URI location = gerarHeaderLocation(saved.getId());

        return ResponseEntity.created(location).body(response);
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> getAll(@AuthenticationPrincipal User user){
        List<Transaction> transactions = service.getAll(user);
        List<TransactionResponseDTO> list = transactions.stream().map(mapper::toResponse).toList();

        return ResponseEntity.ok(list);
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionResponseDTO> findById(@PathVariable("id") UUID id, @AuthenticationPrincipal User user){
        Transaction transaction = service.findById(id, user);
        TransactionResponseDTO response = mapper.toResponse(transaction);

        return ResponseEntity.ok(response);
    }
}
