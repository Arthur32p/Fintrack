package io.github.arthur.Fintrack.mapper;

import io.github.arthur.Fintrack.dto.TransactionRequestDTO;
import io.github.arthur.Fintrack.dto.TransactionResponseDTO;
import io.github.arthur.Fintrack.model.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    public abstract Transaction toEntity(TransactionRequestDTO dto);

    public abstract TransactionRequestDTO toDto(Transaction transaction);

    public abstract TransactionResponseDTO toResponse(Transaction transaction);
}
