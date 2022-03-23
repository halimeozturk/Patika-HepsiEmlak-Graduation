package com.example.advert.service;

import com.example.advert.enums.AdvertStatus;
import com.example.advert.exception.GenericServiceException;
import com.example.advert.model.Advert;
import com.example.advert.repository.AdvertRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListener {

    private final AdvertRepository advertRepository;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void getQueueMessage(Long id) {
        log.info(id.toString());
        Advert advert = advertRepository.findById(id).orElseThrow(() -> new GenericServiceException(GenericServiceException.NOT_FOUND,"Advert not found"));
        advert.setAdvertStatus(AdvertStatus.ACTIVE);
        advertRepository.save(advert);
    }
}
