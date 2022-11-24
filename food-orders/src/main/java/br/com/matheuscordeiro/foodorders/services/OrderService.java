package br.com.matheuscordeiro.foodorders.services;

import br.com.matheuscordeiro.foodorders.dtos.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> findOrders();

    OrderDto findById(Long id);

    OrderDto create(OrderDto orderDto);

    void updateStatus(Long id, OrderDto orderDto);

    void approvePaymentOrder(Long id);
}
