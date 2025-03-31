package com.patos.vendas.exception;

// Exceção personalizada para indicar que um recurso já existe (duplicidade)
public class DuplicateKeyException extends RuntimeException {

    // Construtor que aceita uma mensagem
    public DuplicateKeyException(String message) {
        super(message);
    }

    // Construtor que aceita uma mensagem e uma causa
    public DuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }
}
