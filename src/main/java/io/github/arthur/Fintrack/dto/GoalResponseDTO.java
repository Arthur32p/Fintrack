package io.github.arthur.Fintrack.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record GoalResponseDTO(UUID id, BigDecimal value, LocalDate month) {
}
