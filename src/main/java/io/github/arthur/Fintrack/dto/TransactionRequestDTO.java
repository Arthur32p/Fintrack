package io.github.arthur.Fintrack.dto;

import io.github.arthur.Fintrack.model.CategoryType;
import io.github.arthur.Fintrack.model.TransactionType;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequestDTO(
        @NotNull(message = "Campo obrigatório")
        BigDecimal value,
        @NotNull(message = "Campo obrigatório")
        LocalDate date,
        @NotNull(message = "Campo obrigatório")
        TransactionType transactionType,
        String description,
        @NotNull(message = "Campo obrigatório")
        CategoryType categoryType
)
{ }
