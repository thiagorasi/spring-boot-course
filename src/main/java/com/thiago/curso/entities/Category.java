package com.thiago.curso.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String name;

    private Set<Product> products = new HashSet<>(); // aqui utilizamos SET ao invés de LIST para garantir que não tenhamos 01 produto com mais de uma ocorrência na mesma categoria

    public Category() {
    }

    public Category(Long id, String name, Set<Product> products) { // não instanciamos coleções, pois já são instanciadas na declaração do atributo
        Id = id;
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Product> getProducts() {
        return products;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category category)) return false;
        return Objects.equals(Id, category.Id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Id);
    }
}
