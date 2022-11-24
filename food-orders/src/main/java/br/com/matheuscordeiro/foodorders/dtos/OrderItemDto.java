package br.com.matheuscordeiro.foodorders.dtos;

import br.com.matheuscordeiro.foodorders.models.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {
    private Long id;
    private Integer quantity;
    private String description;
}
