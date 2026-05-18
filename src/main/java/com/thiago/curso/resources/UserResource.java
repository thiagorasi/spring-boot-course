package com.thiago.curso.resources;

import com.thiago.curso.entities.User;
import com.thiago.curso.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController // indica que esta classe é um rest controller
@RequestMapping(value = "/users") // indica o recurso da api
public class UserResource {

    @Autowired // injeção de dependência. No modelo de camadas, o resource DEPENDE do service.
    // mas para isso funcionar, a classe UserService deve estar registrada como COMPONENTE DO SPRING, anotando-a como @Component.
    // mas como UserService é uma classe de serviço, melhor utilizar a anotação @Service ao invés de @Component, pois é mais específica.
    private UserService userService;

    @GetMapping // metodo que responde à requisição do tipo GET do http
    //endpoint para listar todos os usuários
    public ResponseEntity<List<User>> findAll() {
        // removemos o mock que usamos apenas para testar o endpoint...
        // User user = new User(1L, "Thiago", "thiago@example.com", "999999999", "123456");
        List<User> list = userService.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}") // pois este endpoint pede um parametro a ser passado no path
    //endpoint para listar um usuário por id.
    public ResponseEntity<User> findById(@PathVariable(value = "id") Long id) { // estou dizendo para o spring que o id é um path value.
        User user = userService.findById(id);
        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> insert(@RequestBody User obj){ // Anotação necessária para dizer que esse objeto vai chegar no modo Json na hora de fazer a requisição e esse Json vai desserializado para o objeto User
        obj = userService.insert(obj);
        // return ResponseEntity.ok().body(obj); //quando inserimos, a boa prática é retornar 201 e não 200...
        //...por isso é melhor usar .created ao invés de .ok :
        URI uri =  ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri(); // este código serve para criar um URI para o recurso recém criado, ou seja, ele pega a URI da requisição atual, adiciona o ID do usuário recém criado e transforma isso em um URI.
        return ResponseEntity.created(uri).body(obj); // o método created espera um URI como argumento, mas como ainda não temos um endpoint para acessar o usuário recém criado, podemos passar null por enquanto.
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){ // anotação necessária para que o id seja reconhecido como variável da URL
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/{id}") // método http utilizado para atualizar recursos
    public ResponseEntity<User> update(@PathVariable Long id,  @RequestBody User obj){
        obj = userService.update(id, obj);
        return ResponseEntity.ok().body(obj);
    }
}
