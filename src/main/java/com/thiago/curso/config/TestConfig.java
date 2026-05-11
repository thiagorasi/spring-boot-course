package com.thiago.curso.config;

import com.thiago.curso.entities.Category;
import com.thiago.curso.entities.Order;
import com.thiago.curso.entities.Product;
import com.thiago.curso.entities.User;
import com.thiago.curso.entities.enums.OrderStatus;
import com.thiago.curso.repositories.CategoryRepository;
import com.thiago.curso.repositories.OrderRepository;
import com.thiago.curso.repositories.ProductRepository;
import com.thiago.curso.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
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

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        /*
        * Este método é executado quando a aplicação é iniciada. Ele é usado para executar código de inicialização,
        * como carregar dados de teste no banco de dados. No caso, ele limpa o banco de dados e insere um usuário de teste.
         */

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        Product p1 = new Product(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, "");
        Product p2 = new Product(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, "");
        Product p3 = new Product(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, "");
        Product p4 = new Product(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, "");
        Product p5 = new Product(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
        User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

        Order o1 = new Order(null, Instant.parse("2019-06-20T19:53:07Z"), OrderStatus.PAID, u1);
        Order o2 = new Order(null, Instant.parse("2019-07-21T03:42:10Z"), OrderStatus.WAITING_PAYMENT, u2);
        Order o3 = new Order(null, Instant.parse("2019-07-22T15:21:22Z"), OrderStatus.WAITING_PAYMENT, u1);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}
