package com.thiago.curso.resources;

import com.thiago.curso.entities.Category;
import com.thiago.curso.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // indica que esta classe é um rest controller
@RequestMapping(value = "/categories") // indica o recurso da api
public class CategoryResource {

    @Autowired // injeção de dependência. No modelo de camadas, o resource DEPENDE do service.
    // mas para isso funcionar, a classe CategoryService deve estar registrada como COMPONENTE DO SPRING, anotando-a como @Component.
    // mas como CategoryService é uma classe de serviço, melhor utilizar a anotação @Service ao invés de @Component, pois é mais específica.
    private CategoryService categoryService;

    @GetMapping // metodo que responde à requisição do tipo GET do http
    //endpoint para listar todas as categorias
    public ResponseEntity<List<Category>> findAll() {
        // removemos o mock que usamos apenas para testar o endpoint...
        List<Category> list = categoryService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}") // pois este endpoint pede um parametro a ser passado no path
    //endpoint para listar um usuário por id.
    public ResponseEntity<Category> findById(@PathVariable(value = "id") Long id) { // estou dizendo para o spring que o id é um path value.
        Category category = categoryService.findById(id);
        return ResponseEntity.ok().body(category);
    }
}
