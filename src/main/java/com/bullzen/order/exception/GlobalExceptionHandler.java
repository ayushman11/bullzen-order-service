package com.bullzen.order.exception;

import com.bullzen.order.dto.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public ResponseEntity<ApiResponse<Object>> handleOrderNotFound(OrderNotFoundException ex) {

        ApiResponse<Object> response = new ApiResponse<>(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                null
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Object>> handleConstraintViolation(DataIntegrityViolationException e) {
        ApiResponse<Object> response = new ApiResponse<>(
                HttpStatus.CONFLICT.value(),
                "Database constraint violated: " + e.getRootCause().getMessage(),
                null
        );
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationErrors(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining("; "));

        ApiResponse<Object> response = new ApiResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Failed: " + errorMessage,
                null
        );

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGenericErrors(Exception e) {
        ApiResponse<Object> response = new ApiResponse<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Unexpected error: " + e.getMessage(),
                null
        );

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
