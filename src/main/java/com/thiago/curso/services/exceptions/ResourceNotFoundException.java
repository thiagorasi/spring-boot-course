package com.thiago.curso.services.exceptions;

// boa prática criar uma classe de exceção personalizada para cada tipo de erro que pode ocorrer na aplicação, para que seja mais fácil identificar e tratar os erros de forma adequada.
// Neste caso, estamos criando uma classe de exceção personalizada para o erro de recurso não encontrado, ou seja, quando um recurso solicitado pelo cliente não é encontrado no servidor.
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(Object id) {
        super("Resource not found. Id " + id);
    }
}
