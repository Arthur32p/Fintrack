package io.github.arthur.Fintrack.mapper;

import io.github.arthur.Fintrack.dto.UserRequestDTO;
import io.github.arthur.Fintrack.dto.UserResponseDTO;
import io.github.arthur.Fintrack.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public abstract User toEntity(UserRequestDTO dto);

    public abstract UserRequestDTO toDto(User user);

    public abstract UserResponseDTO toResponse(User user);
}
