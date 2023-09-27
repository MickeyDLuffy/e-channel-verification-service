package com.example.echannelverification.controllers.advisor;

import com.example.echannelverification.dtos.ApiExceptionResponse;
import com.example.echannelverification.exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class ApiAdvisor {

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> HandlerException(MethodArgumentNotValidException argument) {
        Map<String, String> errors = new HashMap<>();
        argument.getBindingResult().getFieldErrors().forEach(
                fieldError -> {
                    String fieldName = fieldError.getField();
                    String errorMessage = fieldError.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                    log.error(String.format("%s : %s", fieldName, errorMessage));
                }
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ApiExceptionResponse> HandlerException(TypeMismatchException argument) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse.builder()
                .message(argument.getLocalizedMessage())
                .status(badRequest)
                .build();
        log.error(String.format("%s", argument.getLocalizedMessage()));
        return new ResponseEntity<>(apiExceptionResponse, badRequest);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiExceptionResponse> handleException(ResourceNotFoundException resourceNotFoundException) {
        HttpStatus notFound = HttpStatus.NOT_FOUND;
        ApiExceptionResponse apiExceptionResponse = ApiExceptionResponse
                .builder()
                .message(resourceNotFoundException.getMessage())
                .status(notFound)
                .build();
        log.error(resourceNotFoundException.getMessage());
        return new ResponseEntity<>(apiExceptionResponse, notFound);
    }

}