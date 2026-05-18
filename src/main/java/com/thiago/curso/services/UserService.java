package com.thiago.curso.services;

import com.thiago.curso.entities.User;
import com.thiago.curso.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired //injeção de dependência. No modelo de camdas, o service DEPENDE do repository
    // mas para isso funcionar, a classe UserRepository deve estar registrada como COMPONENTE DO SPRING, anotando-a como @Component.
    // mas como UserRepository é uma classe de respositório, melhor utilizar a anotação @Repository ao invés de @Component, pois é mais específica.
    private UserRepository userRepository;

    public List<User> findAll(){ // operação na camada de serviço que...
        return userRepository.findAll(); //...repassa para a camada de repository
    }

    public User findById(Long id){
        return userRepository.findById(id).get();
    }

    public User insert(User obj){
        return userRepository.save(obj);
    }
}
