package com.thiago.curso.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thiago.curso.entities.enums.OrderStatus;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order") // como Order é uma palavra reservada no JPA, devemos informar por aqui
public class Order implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT") // para formatar a data no formato ISO 8601, que é o formato padrão para datas em JSON. O atributo shape indica que a data deve ser representada como uma string, o atributo pattern define o formato da data e o atributo timezone define o fuso horário.
    private Instant instant;

    // private OrderStatus orderStatus;
    private Integer orderStatus; // aqui, alteramos para interger implicitamente apenas nesta classe,para dizer para banco de dados que estamos gravando um número inteiro do enum.

    @ManyToOne // chave estrangeira
    @JoinColumn(name = "cliend_id") // nome da chave estrangeira
    private User client;

    // representa a associação
    @OneToMany(mappedBy = "id.order") // mapeamento do lado inverso, ou seja, o atributo order da classe OrderItemPk é o responsável por mapear a associação entre Order e OrderItem
    private Set<OrderItem> items =  new HashSet<>();

    public Order() {
    }

    public Order(Long id, Instant instant, OrderStatus orderStatus, User client) {
        this.id = id;
        this.instant = instant;
        setOrderStatus(orderStatus);
        this.client = client;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getInstant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }

    public OrderStatus getOrderStatus() {
        return OrderStatus.valueOf(orderStatus);
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus.getCode();
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Set<OrderItem> getItems() {
        return items;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Order order)) return false;
        return Objects.equals(id, order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
