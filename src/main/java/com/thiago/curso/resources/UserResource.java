package com.thiago.curso.resources;

import com.thiago.curso.entities.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // indica que esta classe é um rest controller
@RequestMapping(value = "/users") // indica o recurso da api
public class UserResource {

    @GetMapping // metodo que responde à requisição do tipo GET do http
    public ResponseEntity<User> findAll() {
        User user = new User(1L, "Thiago", "thiago@example.com", "999999999", "123456");
        return ResponseEntity.ok().body(user);
    }
}
