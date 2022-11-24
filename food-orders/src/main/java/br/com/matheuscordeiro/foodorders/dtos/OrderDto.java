package br.com.matheuscordeiro.foodorders.dtos;

import br.com.matheuscordeiro.foodorders.models.OrderItem;
import br.com.matheuscordeiro.foodorders.models.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private LocalDateTime dateCreation;
    private Status status;
    private List<OrderItem> orderItems = new ArrayList<>();
}
