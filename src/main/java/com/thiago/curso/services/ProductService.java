package com.thiago.curso.services;

import com.thiago.curso.entities.Product;
import com.thiago.curso.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired //injeção de dependência. No modelo de camdas, o service DEPENDE do repository
    // mas para isso funcionar, a classe ProductRepository deve estar registrada como COMPONENTE DO SPRING, anotando-a como @Component.
    // mas como ProductRepository é uma classe de respositório, melhor utilizar a anotação @Repository ao invés de @Component, pois é mais específica.
    private ProductRepository productRepository;

    public List<Product> findAll(){ // operação na camada de serviço que...
        return productRepository.findAll(); //...repassa para a camada de repository
    }

    public Product findById(Long id){
        return productRepository.findById(id).get();
    }
}
