package com.thiago.curso.entities.pk;

import com.thiago.curso.entities.Order;
import com.thiago.curso.entities.Product;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.io.Serializable;
import java.util.Objects;

// classe para representar a chave primária composta da entidade OrderItem,
// ou seja, a chave primária composta é formada por duas chaves estrangeiras,
// uma para a entidade Order e outra para a entidade Product
// isso acontece porque em O.O não existe o conceito de chave primária composta,
// sendo necessário criar uma classe auxiliar para representar o par Product x Order

@Embeddable // classe auxiliar de chave primária composta
public class OrderItemPk implements Serializable {

    @ManyToOne // relacionamento muito para um
    @JoinColumn(name = "order_id") // nome da chave estrangeira na tabela relacional do banco de dados
    private Order order;

    @ManyToOne // relacionamento muito para um
    @JoinColumn(name = "product_id") // nome da chave estrangeira na tabela relacional do banco de dados
    private Product product;

    //esta classe não possui construtores porque se trata de representação
    //de uma chave primária composta, ou seja, ela é usada apenas para representar a chave primária composta
    //e não para criar objetos dessa classe


    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OrderItemPk that)) return false;
        return Objects.equals(order, that.order) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order, product);
    }
}
