package br.com.matheuscordeiro.foodorders.services.impl;

import br.com.matheuscordeiro.foodorders.dtos.OrderDto;
import br.com.matheuscordeiro.foodorders.dtos.StatusDto;
import br.com.matheuscordeiro.foodorders.models.Order;
import br.com.matheuscordeiro.foodorders.models.Status;
import br.com.matheuscordeiro.foodorders.repositories.OrderRepository;
import br.com.matheuscordeiro.foodorders.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;

    private final ModelMapper modelMapper;


    @Override
    public List<OrderDto> findOrders() {
        return orderRepository.findAll().stream()
                .map(order-> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public OrderDto findById(final Long id) {
        return modelMapper.map(orderRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new), OrderDto.class);
    }

    @Override
    public OrderDto create(final OrderDto orderDto) {
        final var order = modelMapper.map(orderDto, Order.class);
        order.setDate(LocalDateTime.now());
        order.setStatus(Status.ACCOMPLISHED);
        order.getOrderItems().forEach(item -> item.setOrder(order));
        return modelMapper.map(orderRepository.save(order), OrderDto.class);
    }

    @Override
    public void updateStatus(final Long id, final StatusDto statusDto) {
        final var order = orderRepository.findByIdWithItems(id).orElseThrow(EntityNotFoundException::new);
        order.setStatus(statusDto.getStatus());
        orderRepository.updateStatus(statusDto.getStatus(), order);
    }

    @Override
    public void approvePaymentOrder(final Long id) {
        final var order = orderRepository.findByIdWithItems(id).orElseThrow(EntityNotFoundException::new);
        order.setStatus(Status.PAID);
        orderRepository.updateStatus(Status.PAID, order);
    }
}
