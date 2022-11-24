package br.com.matheuscordeiro.foodpayments.https;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("order-ms")
public interface OrderClient {
    @RequestMapping(method = RequestMethod.PUT, value = "/orders/{id}/paid")
    void updateStatus(@PathVariable Long id);
}
