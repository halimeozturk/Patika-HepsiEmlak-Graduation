package com.example.payment.service;

import com.example.payment.dto.EmailMessageDTO;

public interface QueueService {

    void createPurchase(String purchaseDTO);

    void sendMessage(EmailMessageDTO mail);

}
