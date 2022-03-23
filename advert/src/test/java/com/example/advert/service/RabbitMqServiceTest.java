package com.example.advert.service;

import com.example.advert.config.RabbitMqConfig;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.verify;

@SpringBootTest
public class RabbitMqServiceTest {

    @InjectMocks
    private RabbitMqService rabbitMqService;

    @Mock
    private AmqpTemplate rabbitTemplate;

    @Mock
    private RabbitMqConfig config;

    @Test
    void changeStatus(){
        Mockito.when(config.getExchange()).thenReturn("advert.status.exchange");
        Mockito.when(config.getRoutingkey()).thenReturn("advert.status.routingkey");
        rabbitMqService.changeStatus(1L);
        verify(rabbitTemplate).convertAndSend(config.getExchange(), config.getRoutingkey(),1L);
     }
}
