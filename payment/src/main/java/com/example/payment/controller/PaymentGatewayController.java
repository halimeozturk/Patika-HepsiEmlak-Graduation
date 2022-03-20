package com.example.payment.controller;

import com.example.payment.client.StripeClient;
import com.example.payment.dto.CardDTO;
import com.stripe.Stripe;
import com.stripe.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequestMapping("/payment")
public class PaymentGatewayController {

    private StripeClient stripeClient;

    @Autowired
    PaymentGatewayController(StripeClient stripeClient) {
        this.stripeClient = stripeClient;
    }

//    @PostMapping
//    public void chargeCard(CardDTO cardDTO) throws Exception {
//        this.stripeClient.chargeNewCard(cardDTO);
//    }

    @PostMapping("/v1/tokens")
    public void getToken(@RequestBody CardDTO cardDTO,@RequestHeader(value = "id") UUID id) throws Exception {
        stripeClient.getToken(cardDTO,id);
    }

}
