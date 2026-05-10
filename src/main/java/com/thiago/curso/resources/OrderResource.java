package com.thiago.curso.resources;

import com.thiago.curso.entities.Order;
import com.thiago.curso.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // indica que esta classe é um rest controller
@RequestMapping(value = "/orders") // indica o recurso da api
public class OrderResource {

    @Autowired // injeção de dependência. No modelo de camadas, o resource DEPENDE do service.
    // mas para isso funcionar, a classe OrderService deve estar registrada como COMPONENTE DO SPRING, anotando-a como @Component.
    // mas como OrderService é uma classe de serviço, melhor utilizar a anotação @Service ao invés de @Component, pois é mais específica.
    private OrderService orderService;

    @GetMapping // metodo que responde à requisição do tipo GET do http
    //endpoint para listar todos os pedidos
    public ResponseEntity<List<Order>> findAll() {
        // removemos o mock que usamos apenas para testar o endpoint...
        // Order order = new order(1L, "Thiago", "thiago@example.com", "999999999", "123456");
        List<Order> list = orderService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}") // pois este endpoint pede um parametro a ser passado no path
    //endpoint para listar um pedido por id.
    public ResponseEntity<Order> findById(@PathVariable(value = "id") Long id) { // estou dizendo para o spring que o id é um path value.
        Order order = orderService.findById(id);
        return ResponseEntity.ok().body(order);
    }
}
