package com.thiago.curso.services;

import com.thiago.curso.entities.Order;
import com.thiago.curso.entities.User;
import com.thiago.curso.repositories.OrderRepository;
import com.thiago.curso.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired //injeção de dependência. No modelo de camdas, o service DEPENDE do repository
    // mas para isso funcionar, a classe UserRepository deve estar registrada como COMPONENTE DO SPRING, anotando-a como @Component.
    // mas como UserRepository é uma classe de respositório, melhor utilizar a anotação @Repository ao invés de @Component, pois é mais específica.
    private OrderRepository orderRepository;

    public List<Order> findAll(){ // operação na camada de serviço que...
        return orderRepository.findAll(); //...repassa para a camada de repository
    }

    public Order findById(Long id){
        return orderRepository.findById(id).get();
    }
}
