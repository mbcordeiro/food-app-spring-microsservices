package br.com.matheuscordeiro.foodpayments.repositories;

import br.com.matheuscordeiro.foodpayments.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
