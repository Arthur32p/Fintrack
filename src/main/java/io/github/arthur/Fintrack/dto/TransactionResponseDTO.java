package io.github.arthur.Fintrack.dto;

import io.github.arthur.Fintrack.model.CategoryType;
import io.github.arthur.Fintrack.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record TransactionResponseDTO(
        UUID id,
        BigDecimal value,
        LocalDate date,
        TransactionType transactionType,
        String description,
        CategoryType categoryType
)
{ }
