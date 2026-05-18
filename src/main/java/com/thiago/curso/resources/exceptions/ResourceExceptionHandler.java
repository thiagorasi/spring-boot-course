package com.thiago.curso.resources.exceptions;

import com.thiago.curso.services.exceptions.DatabaseException;
import com.thiago.curso.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice // anotação necessária para que o Spring reconheça esta classe como um manipulador de exceções global, ou seja, ele vai interceptar todas as exceções lançadas pelos controllers e tratar elas de acordo com os métodos que vamos criar aqui.
public class ResourceExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class) // anotação necessária para indicar que este método vai tratar as exceções do tipo ResourceNotFoundException, ou seja, quando uma exceção do tipo ResourceNotFoundException for lançada, este método vai ser chamado para tratar essa exceção.
    public ResponseEntity<StandardError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND; // 404
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.BAD_REQUEST; // 400
        StandardError err = new StandardError(Instant.now(), status.value(), error, e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
}
