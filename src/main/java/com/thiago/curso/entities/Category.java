package com.thiago.curso.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @JsonIgnore // para evitar o problema de referência cíclica na serialização do JSON. Sem essa anotação, quando o Jackson tenta serializar um objeto Category, ele também tenta serializar os produtos associados a essa categoria. E cada produto, por sua vez, tem uma referência de volta para a categoria, o que leva a um loop infinito de serialização.
    @ManyToMany(mappedBy = "categories") // aqui estamos dizendo que a relação já é mapeada do outro lado, ou seja, na classe Product, onde temos a anotação @JoinTable. O mappedBy indica que esta é a parte inversa da relação, ou seja, a parte que não é responsável por manter a relação no banco de dados.
    private Set<Product> products = new HashSet<>(); // aqui utilizamos SET ao invés de LIST para garantir que não tenhamos 01 produto com mais de uma ocorrência na mesma categoria

    public Category() {
    }

    // não se inclui coleções em construtores, pois já são instanciadas na declaração do atributo
    public Category(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return Objects.equals(id, category.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
