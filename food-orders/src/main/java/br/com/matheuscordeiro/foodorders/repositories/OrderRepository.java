package br.com.matheuscordeiro.foodorders.repositories;

import br.com.matheuscordeiro.foodorders.models.Order;
import br.com.matheuscordeiro.foodorders.models.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query(value = "SELECT o FROM Order o LEFT JOIN FETCH o.orderItems WHERE o.id = :id")
    Optional<Order> findByIdWithItems(Long id);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Order o set o.status = :status where p = :order")
    void updateStatus(Status status, Order order);
}
