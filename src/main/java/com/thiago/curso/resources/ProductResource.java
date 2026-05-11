package com.thiago.curso.resources;

import com.thiago.curso.entities.Product;
import com.thiago.curso.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // indica que esta classe é um rest controller
@RequestMapping(value = "/products") // indica o recurso da api
public class ProductResource {

    @Autowired // injeção de dependência. No modelo de camadas, o resource DEPENDE do service.
    // mas para isso funcionar, a classe ProductService deve estar registrada como COMPONENTE DO SPRING, anotando-a como @Component.
    // mas como ProductService é uma classe de serviço, melhor utilizar a anotação @Service ao invés de @Component, pois é mais específica.
    private ProductService productService;

    @GetMapping // metodo que responde à requisição do tipo GET do http
    //endpoint para listar todos os usuários
    public ResponseEntity<List<Product>> findAll() {
        // removemos o mock que usamos apenas para testar o endpoint...
        List<Product> list = productService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}") // pois este endpoint pede um parametro a ser passado no path
    //endpoint para listar um produto por id.
    public ResponseEntity<Product> findById(@PathVariable(value = "id") Long id) { // estou dizendo para o spring que o id é um path value.
        Product product = productService.findById(id);
        return ResponseEntity.ok().body(product);
    }
}
