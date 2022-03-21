package com.example.email.service;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.example.email.dto.EmailDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RabbitMqListenerService {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(EmailDTO message) {
        log.info(message.toString());
//        emailService.sendMail(message);
    }

}
