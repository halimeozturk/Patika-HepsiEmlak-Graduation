package com.example.payment.controller;

import com.example.payment.client.StripeClient;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/payment")
public class PaymentGatewayController {

    private StripeClient stripeClient;

    @Autowired
    PaymentGatewayController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

    @PostMapping
    public void chargeCard(@RequestHeader(value="token") String token, @RequestHeader(value="amount") double amount,@RequestHeader(value="email") String email) throws Exception {
        this.stripeClient.chargeNewCard(token, amount,email);
    }

}
