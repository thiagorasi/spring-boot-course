package com.thiago.curso.config;

import com.thiago.curso.entities.User;
import com.thiago.curso.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration // Anotação que diz ao framework que esta é uma Classe auxiliar que faz configurações na aplicação.
/**
 * A anotação @Profile é usada para indicar que uma classe ou metodo deve ser ativado apenas para um perfil específico.
 * No caso, a classe TestConfig será ativada apenas quando o perfil "test" estiver ativo.
 * Isso é útil para separar configurações específicas de teste do restante da aplicação,
 * garantindo que elas não sejam carregadas em ambientes de produção ou desenvolvimento.
 * o nome entre parenteses deve ser exatamente igual ao que está no arquivo application-test.properties (ou application-test.yml)
 */
@Profile("test") // indica que esta classe é específica para o perfil de teste
public class TestConfig  implements CommandLineRunner {

    /*
    * mecanismo de injeção de dependência implícito do framework. Injeta de forma automática a dependência do UserRepository
    * neste caso espefício, para o perfil de teste, precisamos acessar o banco de dados,
    * e para isso, dependemos da classe UserRepository.
    * A anotação @Autowired é responsável por dizer ao framework que ela faça esta injeção de dependência.
     */
    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        /*
        * Este método é executado quando a aplicação é iniciada. Ele é usado para executar código de inicialização,
        * como carregar dados de teste no banco de dados. No caso, ele limpa o banco de dados e insere um usuário de teste.
         */
        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2));
    }
}
