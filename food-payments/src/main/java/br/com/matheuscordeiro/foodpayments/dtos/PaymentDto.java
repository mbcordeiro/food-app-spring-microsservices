package br.com.matheuscordeiro.foodpayments.dtos;

import br.com.matheuscordeiro.foodpayments.models.Status;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentDto {
    private Long id;
    private BigDecimal value;
    private String name;
    private String number;
    private String expiration;
    private String code;
    private Status status;
}
