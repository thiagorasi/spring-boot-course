package com.thiago.curso.entities.enums;

public enum OrderStatus {

    WAITING_PAYMENT(1), // boa prática em enums é criar as numerações e não alterá-las, para não quebrar o banco de dados com a mudança
    PAID(2),
    SHIPPED(3),
    DELIVERED(4),
    CANCELED(5);

    private int code;

    private OrderStatus(int code) { // ...e criar um construtor PRIVATE para o código...
        this.code = code;
    }

    public int getCode() { // ...e tornar público o metodo getCode para acessar o código do enum.
        return code;
    }

    public static OrderStatus valueOf(int code) { // metodo para converter um código inteiro em um enum OrderStatus
        for (OrderStatus value : OrderStatus.values()) { // percorre todos os valores do enum
            if (value.getCode() == code) { // compara o código do enum com o código passado como parâmetro
                return value; // se encontrar um enum com o código correspondente, retorna esse enum
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus code"); // se não encontrar nenhum enum com o código correspondente, lança uma exceção
    }
}
