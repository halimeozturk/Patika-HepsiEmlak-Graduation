package com.example.purchase.service;

import com.example.purchase.dto.AdvertPackageDTO;
import com.example.purchase.dto.PurchaseCountDTO;
import com.example.purchase.dto.PurchaseDTO;
import com.example.purchase.enums.PurchaseStatus;
import com.example.purchase.model.PurchaseCount;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.ZonedDateTime;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class RabbitMqListenerService {

    private final PurchaseService purchaseService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(String purchaseDTO) throws UnsupportedEncodingException {
        log.info(purchaseDTO);
        JsonObject convertedObject = new Gson().fromJson(purchaseDTO, JsonObject.class);
        purchaseService.create(jsonToPurchaseDTO(convertedObject),jsonToPurchaseDTO(convertedObject).getUserId());

    }

    public PurchaseDTO jsonToPurchaseDTO(JsonObject convertedObject){
        PurchaseDTO purchaseDTO = new PurchaseDTO();
        AdvertPackageDTO advertPackageDTO = new AdvertPackageDTO();
        advertPackageDTO.setId(convertedObject.get("advertPackageId").getAsLong());
        purchaseDTO.setUserId(UUID.fromString(convertedObject.get("userId").getAsString()));
        purchaseDTO.setPurchaseStatus(PurchaseStatus.valueOf(convertedObject.get("purchaseStatus").getAsString()));
        purchaseDTO.setAdvertPackage(advertPackageDTO);
        purchaseDTO.setPurchaseDate(ZonedDateTime.now());
        purchaseDTO.setPaymentId(convertedObject.get("paymentId").getAsLong());
        return purchaseDTO;

    }

}
