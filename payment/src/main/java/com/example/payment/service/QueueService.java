package com.example.payment.service;

import com.example.payment.dto.EmailMessageDTO;

public interface QueueService {

    void sendMessage(EmailMessageDTO email);

}
