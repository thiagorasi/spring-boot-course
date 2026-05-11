package com.thiago.curso.services;

import com.thiago.curso.entities.Category;
import com.thiago.curso.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired //injeção de dependência. No modelo de camdas, o service DEPENDE do repository
    // mas para isso funcionar, a classe UserRepository deve estar registrada como COMPONENTE DO SPRING, anotando-a como @Component.
    // mas como UserRepository é uma classe de respositório, melhor utilizar a anotação @Repository ao invés de @Component, pois é mais específica.
    private CategoryRepository categoryRepository;

    public List<Category> findAll(){ // operação na camada de serviço que...
        return categoryRepository.findAll(); //...repassa para a camada de repository
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).get();
    }
}
