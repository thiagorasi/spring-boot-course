package com.thiago.curso.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    @ManyToOne // quando temos uma relação MUITO PARA MUITOS, a representação no banco de dados é uma nova tabela de ligação
    @JoinTable(name = "tb_product_category", // nome da tabela de ligação
            joinColumns = @JoinColumn(name = "product_id"), // chave estrangeira que referencia a tabela Product
            inverseJoinColumns = @JoinColumn(name = "category_id")) // chave estrangeira que referencia a tabela Category
    private Set<Category> categories = new HashSet<>(); // aqui utilizamos SET ao invés de LIST para garantir que não tenhamos 01 produto com mais de uma ocorrência na mesma categoria

    public Product() {
    }

    public Product(Long id, String name, String description, Double price, String imgUrl) { // não instanciamos coleções, pois já são instanciadas na declaração do atributo
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Product product)) return false;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
