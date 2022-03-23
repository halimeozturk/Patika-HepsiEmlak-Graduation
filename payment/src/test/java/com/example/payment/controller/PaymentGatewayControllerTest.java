package com.example.payment.controller;

import com.example.payment.client.StripeClient;
import com.example.payment.dto.CardDTO;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class PaymentGatewayControllerTest {
    @InjectMocks
    private PaymentGatewayController paymentGatewayController;

    @Mock
    private StripeClient stripeClient;

    @Test
    void getToken() throws Exception {
        doNothing().when(stripeClient).getToken(any(),any());
        paymentGatewayController.getToken(prepareMockCardDto(), UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        verify(stripeClient).getToken(prepareMockCardDto(),UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
    }

    public CardDTO prepareMockCardDto(){
        CardDTO cardDTO = new CardDTO();
        cardDTO.setToken("tok-www");
        cardDTO.setUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        cardDTO.setAdvertPackageId(1L);
        cardDTO.setEmail("ozturkk.halimee@gmail.com");
        cardDTO.setAmount(3.0);
        return cardDTO;
    }

}
