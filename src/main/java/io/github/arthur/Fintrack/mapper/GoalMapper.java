package io.github.arthur.Fintrack.mapper;

import io.github.arthur.Fintrack.dto.GoalRequestDTO;
import io.github.arthur.Fintrack.dto.GoalResponseDTO;
import io.github.arthur.Fintrack.model.Goal;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    public abstract Goal toEntity(GoalRequestDTO dto);

    public abstract GoalResponseDTO toResponse(Goal goal);

    public abstract GoalRequestDTO toDto(Goal goal);
}
