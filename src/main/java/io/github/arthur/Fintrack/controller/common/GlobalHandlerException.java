package io.github.arthur.Fintrack.controller.common;

import io.github.arthur.Fintrack.dto.ErrorResponse;
import io.github.arthur.Fintrack.exceptions.DuplicateRecordException;
import io.github.arthur.Fintrack.exceptions.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handlerMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<io.github.arthur.Fintrack.dto.FieldError> errorList = fieldErrors
                                                                    .stream()
                                                                    .map(fe -> new io.github.arthur.Fintrack.dto.FieldError(fe.getField(), fe.getDefaultMessage())).toList();

        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", errorList);
    }

    @ExceptionHandler(DuplicateRecordException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ErrorResponse handlerDuplicateRecordException(DuplicateRecordException e){
        return ErrorResponse.conflict(e.getMessage());
    }

    @ExceptionHandler(RecordNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerRecordNotFoundException(RecordNotFoundException e){
        return ErrorResponse.notFound(e.getMessage());
    }
}
