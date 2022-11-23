package br.com.matheuscordeiro.foodpayments.dtos;

import br.com.matheuscordeiro.foodpayments.models.Status;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
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
