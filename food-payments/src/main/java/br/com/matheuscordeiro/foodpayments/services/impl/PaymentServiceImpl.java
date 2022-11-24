package br.com.matheuscordeiro.foodpayments.services.impl;

import br.com.matheuscordeiro.foodpayments.dtos.PaymentDto;
import br.com.matheuscordeiro.foodpayments.https.OrderClient;
import br.com.matheuscordeiro.foodpayments.models.Payment;
import br.com.matheuscordeiro.foodpayments.models.Status;
import br.com.matheuscordeiro.foodpayments.repositories.PaymentRepository;
import br.com.matheuscordeiro.foodpayments.services.PaymentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    private final ModelMapper modelMapper;

    private final OrderClient orderClient;

    @Override
    public Page<PaymentDto> findPayments(final Pageable pageable) {
        return paymentRepository.findAll(pageable).map(payment -> modelMapper.map(payment, PaymentDto.class));
    }

    @Override
    public PaymentDto findById(final Long id) {
        return paymentRepository.findById(id).map(payment -> modelMapper.map(payment, PaymentDto.class))
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public PaymentDto create(final PaymentDto paymentDto) {
        final var payment = modelMapper.map(paymentDto, Payment.class);
        payment.setStatus(Status.CREATED);
        return modelMapper.map(paymentRepository.save(payment), PaymentDto.class);
    }

    @Override
    @Transactional
    public void update(final Long id, final PaymentDto paymentDto) {
        paymentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        paymentRepository.save(modelMapper.map(paymentDto, Payment.class));
    }

    @Override
    public void delete(final Long id) {
        paymentRepository.findById(id);
    }

    @Override
    public void confirmPayment(Long id) {
        final var payment = paymentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        payment.setStatus(Status.CONFIRMED);
        paymentRepository.save(payment);
        orderClient.updateStatus(payment.getOrderId());
    }

    @Override
    public void updateStatus(Long id) {
        final var payment = paymentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        payment.setStatus(Status.CONFIRMED_WITHOUT_INTEGRATION);
        paymentRepository.save(payment);
    }
}
