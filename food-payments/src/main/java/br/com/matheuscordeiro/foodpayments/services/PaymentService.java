package br.com.matheuscordeiro.foodpayments.services;

import br.com.matheuscordeiro.foodpayments.dtos.PaymentDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PaymentService {
    Page<PaymentDto> findPayments(Pageable pageable);

    PaymentDto findById(Long id);

    PaymentDto create(PaymentDto paymentDto);

    PaymentDto update(Long id, PaymentDto paymentDto);

    void delete(Long id);
}