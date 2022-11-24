package br.com.matheuscordeiro.foodorders;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class FoodOrdersApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodOrdersApplication.class, args);
	}

}
