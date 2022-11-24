package br.com.matheuscordeiro.foodorders.services;

import br.com.matheuscordeiro.foodorders.dtos.OrderDto;
import br.com.matheuscordeiro.foodorders.dtos.StatusDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> findOrders();

    OrderDto findById(Long id);

    OrderDto create(OrderDto orderDto);

    void updateStatus(Long id, StatusDto statusDto);

    void approvePaymentOrder(Long id);
}
