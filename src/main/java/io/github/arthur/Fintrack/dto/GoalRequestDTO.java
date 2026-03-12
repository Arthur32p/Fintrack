package io.github.arthur.Fintrack.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

public record GoalRequestDTO(@NotNull(message = "Campo obrigatório") BigDecimal value, @NotNull(message = "Campo obrigatório") LocalDate month) {
}
