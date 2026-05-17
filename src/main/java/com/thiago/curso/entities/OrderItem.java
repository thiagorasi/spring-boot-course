package com.thiago.curso.entities;

import com.thiago.curso.entities.pk.OrderItemPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

    @EmbeddedId // anotação para indicar que a chave primária é composta por uma classe auxiliar, ou seja, a classe OrderItemPk
    private OrderItemPk id; // chave primária composta representada na tabela OrderItemPk

    private Integer quantity;
    private Double price;

    public OrderItem() {
    }

    // neste construtor, desmarcamos o id ao criar pois
    // o id de cada um serão os atributos order e product, ou seja, a chave primária composta é formada por esses dois atributos...
    public OrderItem(Order order, Product product, Integer quantity, Double price) {
        super();
        id.setOrder(order); //...setados no id por aqui
        id.setProduct(product); //...setados no id por aqui
        this.quantity = quantity;
        this.price = price;
    }

    public Order getOrder() {
        return id.getOrder();
    }
    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct () {
        return id.getProduct();
    }
    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderItem orderItem)) return false;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
