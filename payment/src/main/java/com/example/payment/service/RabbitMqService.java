package com.example.payment.service;
import com.example.payment.config.RabbitMqConfig;
import com.example.payment.dto.EmailMessageDTO;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class RabbitMqService implements QueueService {

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private RabbitMqConfig config;

    @Override
    public void createPurchase(String purchaseDTO) {
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), purchaseDTO);
    }

    @Override
    public void sendMessage(EmailMessageDTO mail) {
        rabbitTemplate.convertAndSend(config.getExchangeMail(), config.getRoutingkeyMail(), mail);
    }
}
