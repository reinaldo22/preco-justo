package com.patos.vendas.exception;

// Exceção personalizada para indicar que o recurso não foi encontrado
public class NotFound extends RuntimeException {

    // Construtor que aceita uma mensagem
    public NotFound(String message) {
        super(message);
    }

    // Construtor que aceita uma mensagem e uma causa
    public NotFound(String message, Throwable cause) {
        super(message, cause);
    }
}

