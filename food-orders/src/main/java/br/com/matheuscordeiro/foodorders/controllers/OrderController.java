package br.com.matheuscordeiro.foodorders.controllers;

import br.com.matheuscordeiro.foodorders.dtos.OrderDto;
import br.com.matheuscordeiro.foodorders.dtos.StatusDto;
import br.com.matheuscordeiro.foodorders.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> list() {
        return ResponseEntity.ok(orderService.findOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping()
    public ResponseEntity<OrderDto> create(@RequestBody @Valid OrderDto orderDto, UriComponentsBuilder uriBuilder) {
        final var order = orderService.create(orderDto);
        return ResponseEntity.created(uriBuilder.path("/orders/{id}").buildAndExpand(order.getId()).toUri()).body(order);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable Long id, @RequestBody StatusDto statusDto){
        orderService.updateStatus(id, statusDto);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/pago")
    public ResponseEntity<Void> approvePayment(@PathVariable @NotNull Long id) {
        orderService.approvePaymentOrder(id);
        return ResponseEntity.noContent().build();
    }
}
