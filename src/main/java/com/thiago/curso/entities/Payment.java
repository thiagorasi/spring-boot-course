package com.thiago.curso.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name ="tb_payment")
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ig;
    private Instant moment;

    //associação
    @OneToOne
    @MapsId // esta anotação serve para
    private Order order;

    public Payment (){
    }

    public Payment(Order order, Instant moment, Long ig) {
        this.order = order;
        this.moment = moment;
        this.ig = ig;
    }

    public Long getIg() {
        return ig;
    }

    public void setIg(Long ig) {
        this.ig = ig;
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
        return Objects.equals(ig, payment.ig);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(ig);
    }
}
