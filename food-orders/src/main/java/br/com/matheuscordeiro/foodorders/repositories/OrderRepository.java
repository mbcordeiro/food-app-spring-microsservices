package br.com.matheuscordeiro.foodorders.repositories;

import br.com.matheuscordeiro.foodorders.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
