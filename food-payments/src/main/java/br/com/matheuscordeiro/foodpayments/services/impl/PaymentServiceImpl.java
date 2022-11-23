package br.com.matheuscordeiro.foodpayments.services.impl;

import br.com.matheuscordeiro.foodpayments.dtos.PaymentDto;
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

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;

    private ModelMapper modelMapper;

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
    public PaymentDto create(final PaymentDto paymentDto) {
        final var payment = modelMapper.map(paymentDto, Payment.class);
        payment.setStatus(Status.CREATED);
        return modelMapper.map(paymentRepository.save(payment), PaymentDto.class);
    }

    @Override
    public void update(final Long id, final PaymentDto paymentDto) {
        paymentRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        paymentRepository.save(modelMapper.map(paymentDto, Payment.class));
    }

    @Override
    public void delete(final Long id) {
        paymentRepository.findById(id);
    }
}
