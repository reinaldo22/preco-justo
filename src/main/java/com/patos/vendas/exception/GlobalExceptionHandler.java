package com.patos.vendas.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Tratamento para recursos duplicados
    @ExceptionHandler(DuplicateKeyException.class)
    public ResponseEntity<Object> handleDuplicatedResourceException(DuplicateKeyException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Duplicação de recurso", ex.getMessage());
    }

    // Tratamento para recursos não encontrados
    @ExceptionHandler(NotFound.class)
    public ResponseEntity<Object> handleNotFoundException(NotFound ex) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, "Recurso não encontrado", ex.getMessage());
    }

    // Tratamento para recursos não encontrados
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentExceptionException(IllegalArgumentException ex) {
        return buildErrorResponse(HttpStatus.CONFLICT, "Conflito", ex.getMessage());
    }

    // Tratamento para parâmetros obrigatórios ausentes
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<Object> handleMissingParams(MissingServletRequestParameterException ex) {
        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Parâmetro ausente", ex.getMessage());
    }

    // Tratamento para validação de campos (Bean Validation)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Erro de validação");
        response.put("message", errors);

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    // Tratamento genérico para outras exceções não tratadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGenericException(Exception ex) {
        return buildErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor", ex.getMessage());
    }

    // Método auxiliar para montar a resposta JSON
    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String error, String message) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("status", status.value());
        body.put("error", error);
        body.put("message", message);

        return new ResponseEntity<>(body, status);
    }
}
