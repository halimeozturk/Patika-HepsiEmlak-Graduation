package com.example.payment.service;

import com.example.payment.config.RabbitMqConfig;
import com.example.payment.dto.EmailMessageDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class RabbitMqServiceTest {
    @InjectMocks
    private RabbitMqService rabbitMqService;

    @Mock
    private AmqpTemplate rabbitTemplate;

    @Mock
    private RabbitMqConfig config;


    private EmailMessageDTO prepareEmailDTO(){
        EmailMessageDTO emailMessageDTO = new EmailMessageDTO();
        emailMessageDTO.setEmail("ozturkk.halimee@gmail.com");
        emailMessageDTO.setMessage("test");
        emailMessageDTO.setPackageName("test");
        emailMessageDTO.setAdvertPackageId(1L);
        emailMessageDTO.setDuration(1);
        emailMessageDTO.setPrice(1);
        emailMessageDTO.setCurrency("TL");
        emailMessageDTO.setPaymentStatus("ACTIVE");
        emailMessageDTO.setUserId(UUID.fromString("a5ed470d-af7f-459a-a9c1-952c202b4f93"));
        return emailMessageDTO;
    }

    @Test
    void sendMessage(){
        Mockito.when(config.getExchange()).thenReturn("payment.exchange");
        Mockito.when(config.getRoutingkey()).thenReturn("payment.routingkey");

        rabbitMqService.sendMessage(prepareEmailDTO());
        verify(rabbitTemplate).convertAndSend(config.getExchange(), config.getRoutingkey(),prepareEmailDTO());
    }

    @Test
    void createPurchase(){
        Mockito.when(config.getExchange()).thenReturn("rabbitmq.exchange");
        Mockito.when(config.getRoutingkey()).thenReturn("rabbitmq.routingkey");

        rabbitMqService.createPurchase("test");
        verify(rabbitTemplate).convertAndSend(config.getExchange(), config.getRoutingkey(),"test");
    }
}
