package com.thiago.curso.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.thiago.curso.entities.pk.OrderItemPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

    @EmbeddedId // anotação para indicar que a chave primária é composta por uma classe auxiliar, ou seja, a classe OrderItemPk
    // chave primária composta representada na tabela OrderItemPk
    // sempre que for criar uma classe auxiliar que é o ID COMPOSTO, É NECESSÁRIO INSTANCIAR E NÃO APENAS CRIAR O ATRIBUTO.
    private OrderItemPk id = new OrderItemPk();

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

    @JsonIgnore // para evitar que o JSON entre em loop infinito, ou seja, quando o JSON for serializado, ele vai ignorar este método, ou seja, ele não vai serializar o atributo order, evitando assim o loop infinito.
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

    //metodo subtotal
    // public subTotal() { // mas para aparecer no Json...
    public Double getSubTotal() { // ... a plataforma requer get.
        return price * quantity;
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
