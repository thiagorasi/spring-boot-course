package com.thiago.curso.services;

import com.thiago.curso.entities.User;
import com.thiago.curso.repositories.UserRepository;
import com.thiago.curso.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<User> user = userRepository.findById(id);
        // return userRepository.findById(id).get(); // isso até funciona, mas não trata o caso de recurso não encontrado e retorna 500, o que não é boa prática neste caso.
        return user.orElseThrow(() -> new ResourceNotFoundException(id)); // este código é para tratar o caso de o usuário não ser encontrado, ou seja, ele retorna um Optional e se o usuário não for encontrado, ele lança uma exceção personalizada ResourceNotFoundException.
    }

    public User insert(User obj){
        return userRepository.save(obj);
    }

    public void delete(Long id){
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(e.getMessage());
        }
    }

    public User update(Long id, User obj){ // id para indicar qual usuáiro será atualizado e obj para indicar os novos dados do usuário
        try {
            User user = userRepository.getOne(id); // este método getReferenceById é um método do JPA que retorna uma referência para o objeto com o ID especificado, sem acessar o banco de dados. Ele é útil para atualizar um objeto, pois ele permite que você obtenha uma referência para o objeto sem precisar carregá-lo completamente do banco de dados. Isso é especialmente útil quando você só precisa atualizar alguns campos do objeto e não precisa acessar todos os seus dados.
            updateData(user, obj); // método auxiliar para atualizar os dados do usuário
            return userRepository.save(user); // salva o usuário atualizado no banco de dados
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(User user, User obj) {
        user.setName(obj.getName());
        user.setEmail(obj.getEmail());
        user.setPhone(obj.getPhone());
    }
}
