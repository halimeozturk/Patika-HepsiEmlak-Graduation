package com.example.advert.service;

import com.example.advert.config.RabbitMqConfig;
import com.example.advert.dto.AdvertDTO;
import com.example.advert.model.Advert;
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
    public void changeStatus(Long id) {
        rabbitTemplate.convertAndSend(config.getExchange(), config.getRoutingkey(), id);
    }

}
