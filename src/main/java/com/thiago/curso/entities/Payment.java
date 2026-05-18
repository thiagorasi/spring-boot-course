package com.thiago.curso.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name ="tb_payment")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant moment;

    //associação
    @JsonIgnore // para evitar que o JSON entre em loop infinito, ou seja, quando o JSON for serializado, ele vai ignorar este método, ou seja, ele não vai serializar o atributo order, evitando assim o loop infinito.
    @OneToOne
    @MapsId // esta anotação serve para indicar que o ID da entidade Payment é o mesmo que o ID da entidade Order, ou seja, a chave primária de Payment é a mesma chave primária de Order. Isso é necessário porque a associação entre Payment e Order é do tipo um-para-um, ou seja, cada pagamento está associado a um único pedido e vice-versa. Com essa anotação, o JPA entende que o ID de Payment deve ser igual ao ID de Order, garantindo assim a integridade referencial entre as duas entidades.
    private Order order;

    public Payment () {
    }

    public Payment(Long id, Instant moment, Order order) {
        super();
        this.id = id;
        this.moment = moment;
        this.order = order;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getMoment() {
        return moment;
    }

    public void setMoment(Instant moment) {
        this.moment = moment;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Payment payment)) return false;
        return Objects.equals(id, payment.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
