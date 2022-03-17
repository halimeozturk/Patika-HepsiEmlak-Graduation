package com.example.purchase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PurchaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(PurchaseApplication.class, args);
    }

}
