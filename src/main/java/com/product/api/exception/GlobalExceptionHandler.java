package com.product.api.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.product.api.Dtos.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ErrorResponse> handleInvalidProduct(InvalidProductException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // @ExceptionHandler(Exception.class) // fallback geral
    // public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
    //     ErrorResponse error = new ErrorResponse("Erro interno no servidor", HttpStatus.INTERNAL_SERVER_ERROR.value());
    //     return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    // }

    @ExceptionHandler(IncorrectEmailOrPassordException.class)
    public ResponseEntity<ErrorResponse> handleIncorrectEmailOrPassord(IncorrectEmailOrPassordException ex) {
        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDuplicateKey(DataIntegrityViolationException ex) {

        String message = ex.getMostSpecificCause().getMessage();

        if (message.contains("uq_users_email")) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Este email já está cadastrado!");
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Erro de integridade no banco de dados.");
    }
}





