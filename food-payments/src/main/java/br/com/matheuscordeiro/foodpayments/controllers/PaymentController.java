package br.com.matheuscordeiro.foodpayments.controllers;

import br.com.matheuscordeiro.foodpayments.dtos.PaymentDto;
import br.com.matheuscordeiro.foodpayments.services.PaymentService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/payments")
@RequiredArgsConstructor
public class PaymentController {
    private PaymentService paymentService;

    @GetMapping
    public ResponseEntity<Page<PaymentDto>> list(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(paymentService.findPayments(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> listById(@PathVariable @NotNull Long id) {
        return ResponseEntity.ok(paymentService.findById(id));
    }

    @PostMapping
    public ResponseEntity<PaymentDto> create(@RequestBody @Valid PaymentDto paymentDto, UriComponentsBuilder uriComponentsBuilder) {
        return ResponseEntity.created(uriComponentsBuilder.path("payments/{id}")
                .buildAndExpand(paymentService.create(paymentDto).getId()).toUri()).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@PathVariable @NotNull Long id, @RequestBody @Valid PaymentDto paymentDto) {
        paymentService.update(id, paymentDto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @NotNull Long id) {
        paymentService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/confirm")
    @CircuitBreaker(name = "updateStatus", fallbackMethod = "paymentAuthorizedPendingIntegration")
    public void confirm(@PathVariable @NotNull Long id){
        paymentService.confirmPayment(id);
    }

    public void paymentAuthorizedPendingIntegration(Long id, Exception e) {
        paymentService.updateStatus(id);
    }
}
