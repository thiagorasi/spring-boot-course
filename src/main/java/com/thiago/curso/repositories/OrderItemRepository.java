package com.thiago.curso.repositories;

import com.thiago.curso.entities.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // esta anotação, neste caso, não é obrigatória, pois esta classe já é uma extensão do JpaRepository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}

